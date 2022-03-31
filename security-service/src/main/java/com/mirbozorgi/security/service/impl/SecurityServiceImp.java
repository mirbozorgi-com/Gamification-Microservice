package com.mirbozorgi.security.service.impl;

import com.mirbozorgi.security.constant.EnumRole;
import com.mirbozorgi.security.domain.AuthorizationInfo;
import com.mirbozorgi.security.domain.LoginInfo;
import com.mirbozorgi.security.domain.RegisterInfo;
import com.mirbozorgi.security.entity.EmailForm;
import com.mirbozorgi.security.entity.User;
import com.mirbozorgi.security.exception.EmailVerifiedException;
import com.mirbozorgi.security.exception.ForgetPassTokenException;
import com.mirbozorgi.security.exception.ForgetPassTokenExpiredException;
import com.mirbozorgi.security.exception.PasswordException;
import com.mirbozorgi.security.exception.UserDoseNotExistedException;
import com.mirbozorgi.security.exception.UserExistAndEmailWrongException;
import com.mirbozorgi.security.exception.UserExistedException;
import com.mirbozorgi.security.exception.WrongVerificationCodeException;
import com.mirbozorgi.security.mailService.ContextService;
import com.mirbozorgi.security.mailService.EmailService;
import com.mirbozorgi.security.mapper.UserMapper;
import com.mirbozorgi.security.repository.entity.EmailFormRepository;
import com.mirbozorgi.security.repository.entity.SecurityRepository;
import com.mirbozorgi.security.repository.memory.PlayerSessionRepository;
import com.mirbozorgi.security.service.JwtService;
import com.mirbozorgi.security.service.SecurityService;
import com.mirbozorgi.security.service.StringService;
import com.mirbozorgi.security.service.TimeService;
import java.util.List;
import javax.transaction.Transactional;
import mirbozorgi.base.domain.security.GetByEmailInfo;
import mirbozorgi.base.domain.security.GetDeviceIdInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Service
public class SecurityServiceImp implements SecurityService {

  @Autowired
  private StringService stringService;

  @Autowired
  private SecurityRepository securityRepository;

  @Autowired
  private JwtService jwtService;

  @Value("${jwt.server.secret}")
  private String secret;


  @Autowired
  private EmailService emailService;


  @Autowired
  private ContextService contextService;

  @Autowired
  private TimeService timeService;

  @Autowired
  private PlayerSessionRepository playerSessionRepository;

  @Autowired
  private SpringTemplateEngine templateEngine;

  @Autowired
  private EmailFormRepository emailFormRepository;

  @Override
  @Transactional
  public RegisterInfo userRegister(
      String email,
      String password,
      String deviceId,
      String globalUniqueId,
      String uuid,
      String gamePackageName,
      String env,
      String marketName) {
    email = toLowerCase(email);
    String verificationCode = stringService.generateRandomString(true, true, 6);
    String sessionId = stringService.generateRandomString(true, true, 10);
    /////check exceptions
    User byEmail = securityRepository.getByEmailInAllMarket(
        email,
        gamePackageName,
        env);

    if (byEmail != null) {
      if (byEmail.getVerifyEmail()) {
        throw new UserExistedException();
      } else {
        throw new EmailVerifiedException();
      }
    }

    if (password != null) {
      password = stringService.encodeBase64WithSalt(password);
    }

    User userFounded = securityRepository.getByUuId(uuid,
        gamePackageName,
        marketName,
        env);

    User userByRoleAndDeviceId = securityRepository.getByRoleAndDeviceId(
        EnumRole.USER,
        deviceId,
        gamePackageName,
        env,
        marketName);

    if (userFounded != null) {
      if (userFounded.getEmail() != null && userFounded.getVerifyEmail()) {
        throw new UserExistAndEmailWrongException();
      }

      if (userByRoleAndDeviceId != null) {
        securityRepository.updateGlobalAndDeviceId(
            userByRoleAndDeviceId.getEmail(),
            userByRoleAndDeviceId.getGlobalUniqueId(),
            userByRoleAndDeviceId.getDeviceId(),
            userByRoleAndDeviceId.getGamePackageName(),
            userByRoleAndDeviceId.getEnv(),
            userByRoleAndDeviceId.getMarket()

        );
      }
      if (securityRepository.updateGuestToUser(
          userFounded.getUuid(),
          password,
          email,
          globalUniqueId,
          deviceId,
          EnumRole.USER,
          verificationCode,
          sessionId,
          gamePackageName,
          env)) {
        securityRepository.regenerateVerificationCode(
            email,
            verificationCode,
            timeService.getNowUnixFromInstantClass());

        try {
          emailService.sendEmail(
              contextService.getEmailFrom(),
              email,
              "فعال سازی حساب کاربری",
              getContentForm(verificationCode, "verification", env, gamePackageName)

          );
        } catch (Exception ignored) {
        }

        return UserMapper.toRegisterInfo(
            new User(userFounded.getUuid(),
                EnumRole.USER,
                "",
                email,
                globalUniqueId,
                deviceId,
                false,
                verificationCode,
                "",
                0,
                0,
                timeService.getNowUnixFromInstantClass(),
                timeService.getNowUnixFromInstantClass(),
                sessionId,
                gamePackageName,
                env,
                marketName)

        );
      }


    }
    //user set wrong email and want to update email
    if (userByRoleAndDeviceId != null) {
      if (userByRoleAndDeviceId.getEmail() != null && !userByRoleAndDeviceId.getVerifyEmail()) {
        securityRepository.updateEmail(deviceId, gamePackageName, env, marketName, email, false);
        securityRepository.regenerateVerificationCode(
            email,
            verificationCode,
            timeService.getNowUnixFromInstantClass());
        try {
          emailService.sendEmail(
              contextService.getEmailFrom(),
              email,
              "فعال سازی حساب کاربری",
              getContentForm(verificationCode, "verification", env, gamePackageName)

          );
        } catch (Exception ignored) {
        }
        return UserMapper.toRegisterInfo(userByRoleAndDeviceId);

      }
    }

    User user = securityRepository.register(new User(
        stringService.generateRandomString(true, true, 15),
        EnumRole.USER,
        password,
        email,
        globalUniqueId,
        deviceId,
        false,
        verificationCode,
        "",
        0,
        0,
        timeService.getNowUnixFromInstantClass(),
        timeService.getNowUnixFromInstantClass(),
        sessionId,
        gamePackageName,
        env,
        marketName
    ));

    try {

      emailService.sendEmail(
          contextService.getEmailFrom(),
          email,
          "فعال سازی حساب کاربری",
          getContentForm(verificationCode, "verification", env, gamePackageName)

      );
    } catch (Exception ignored) {
    }

    return UserMapper.toRegisterInfo(user);
  }

  @Transactional
  @Override
  public LoginInfo login(
      String email,
      String password,
      String globalUniqueId,
      String deviceId,
      String gamePackageName,
      String env,
      String marketName) {
    email = toLowerCase(email);
    String sessionId = stringService.generateRandomString(true, true, 10);
    String token = null;

    // we want to have same uuid for all user's market
    // profile so if user change him/her market
    // we should create user market profile again
    List<User> userForFindingUuId = securityRepository.getWithRoleAndDeviceAndEmail(
        email,
        "",
        gamePackageName,
        env,
        EnumRole.USER);
    boolean shouldAddProfile = true;
    if (userForFindingUuId != null && userForFindingUuId.size() != 0) {
      for (User user : userForFindingUuId) {
        if (user.getMarket().equals(marketName)) {
          shouldAddProfile = false;
          break;
        }
      }
      if (shouldAddProfile) {
        securityRepository.register(
            new User(
                userForFindingUuId.get(0).getUuid(),
                userForFindingUuId.get(0).getRole(),
                userForFindingUuId.get(0).getPassword(),
                userForFindingUuId.get(0).getEmail(),
                userForFindingUuId.get(0).getGlobalUniqueId(),
                userForFindingUuId.get(0).getDeviceId(),
                userForFindingUuId.get(0).getVerifyEmail(),
                userForFindingUuId.get(0).getVerificationCode(),
                userForFindingUuId.get(0).getForgetPassToken(),
                userForFindingUuId.get(0).getCreatedForgetPassTokenDate(),
                userForFindingUuId.get(0).getVerifiedDate(),
                userForFindingUuId.get(0).getVerificationCodeCreatedDate(),
                userForFindingUuId.get(0).getCreatedDate(),
                userForFindingUuId.get(0).getSessionId(),
                userForFindingUuId.get(0).getGamePackageName(),
                userForFindingUuId.get(0).getEnv(),
                marketName
            ));
      }
    }

    User userFounded = securityRepository.getByEmail(email,
        gamePackageName,
        env,
        marketName);
    if (userFounded == null) {
      throw new UserDoseNotExistedException();
    }
    if (!stringService.decodeBase64WithSalt(
        userFounded.getPassword()).equals(password)) {
      throw new PasswordException();
    }

    if (!userFounded.getVerifyEmail()) {
      throw new EmailVerifiedException();
    }
    if (globalUniqueId == null || deviceId.equals("")) {
      globalUniqueId = userFounded.getGlobalUniqueId();
    }

    if (deviceId == null || deviceId.equals("")) {
      deviceId = userFounded.getDeviceId();
    }
    securityRepository.updateSessionId(
        userFounded.getUuid(),
        gamePackageName,
        marketName,
        env,
        sessionId
    );
    playerSessionRepository.remove(
        userFounded.getUuid(),
        gamePackageName,
        marketName,
        env
    );
    playerSessionRepository.add(
        userFounded.getUuid(),
        sessionId,
        gamePackageName,
        marketName,
        env
    );

    if (!userFounded.getGlobalUniqueId().equals(globalUniqueId) ||
        !userFounded.getDeviceId().equals(deviceId)) {

      User anotherUser = securityRepository.getByRoleAndDeviceId(
          EnumRole.USER,
          deviceId,
          gamePackageName,
          env,
          marketName);

      //update another user founded with this deviceId
      if (anotherUser != null) {
        securityRepository.updateGlobalAndDeviceId(
            anotherUser.getEmail(),
            anotherUser.getUuid(),
            anotherUser.getUuid(),
            gamePackageName,
            env,
            marketName);
      }

      //update current user deviceId
      securityRepository.updateGlobalAndDeviceId(
          email,
          globalUniqueId,
          deviceId,
          gamePackageName,
          env,
          marketName);
      token = jwtService.createToken(
          email,
          userFounded.getRole(),
          userFounded.getUuid(),
          globalUniqueId,
          deviceId,
          sessionId
      );
      return new LoginInfo(token, userFounded.getUuid());

    }

    token = jwtService.createToken(
        email,
        userFounded.getRole(),
        userFounded.getUuid(),
        userFounded.getGlobalUniqueId(),
        userFounded.getDeviceId(),
        sessionId

    );
    return new LoginInfo(token, userFounded.getUuid());

  }

  @Override
  public GetDeviceIdInfo getDeviceId(String uuid,
      String gamePackageName,
      String env,
      String marketName) {
    String deviceId = securityRepository.getDeviceId(
        uuid, gamePackageName, env, marketName);
    return new GetDeviceIdInfo(deviceId);
  }

  @Override
  public GetByEmailInfo getBy(
      String email,
      String uuid,
      String gamePackageName,
      String env,
      String marketName) {
    User user = null;
    if (email != null) {
      email = toLowerCase(email);
      user = securityRepository.getByEmail(
          email,
          gamePackageName,
          env,
          marketName);
    } else if (uuid != null) {
      user = securityRepository.getByUuId(uuid,
          gamePackageName,
          marketName,
          env);
    }
    if (user == null) {
      throw new UserDoseNotExistedException();
    }
    return new GetByEmailInfo(
        user.getDeviceId(),
        user.getUuid()
    );
  }


  @Override
  public AuthorizationInfo authorize(
      String token,
      String gamePackageName,
      String env,
      String marketName) {
    AuthorizationInfo authorize = jwtService.authorize(token);

    //TODO : bazbini bayad shawad baraye session management va aslan pak nashe
//    if (!authorize.getRole().equals(EnumRole.GUEST_USER.toString())) {
//      String sessionId = playerSessionRepository.get(
//          authorize.getUuid(),
//          gamePackageName,
//          marketName,
//          env);
//      if (sessionId == null) {
//        User user = securityRepository.getByUuId(authorize.getUuid(),
//            gamePackageName,
//            marketName,
//            env);
//        if (user == null) {
//          throw new UserDoseNotExistedException();
//        }
//        sessionId = user.getSessionId();
//        playerSessionRepository.add(user.getUuid(),
//            sessionId,
//            gamePackageName,
//            marketName,
//            env);
//      }
//      if (!authorize.getSessionId().equals(sessionId)) {
//        throw new SessionIdException();
//      }
//    }

    return authorize;
  }

  @Override
  public void verify(
      String email,
      String gamePackageName,
      String env,
      String marketName,
      String verificationCode) {
    email = toLowerCase(email);
    User user = securityRepository.getByEmailInAllMarket(
        email,
        gamePackageName,
        env);
    if (user == null) {
      throw new UserDoseNotExistedException();
    }

    if (!user.getVerificationCode().equals(verificationCode)) {
      throw new WrongVerificationCodeException();
    }

    //limit for verification should be done in 1 hour
    if (timeService.getNowUnixFromInstantClass() - user.getVerificationCodeCreatedDate() >= 3600) {
      throw new WrongVerificationCodeException();
    }

    securityRepository.verify(
        email,
        true,
        timeService.getNowUnixFromInstantClass()
    );


  }

  @Transactional
  @Override
  public void reGenerateVerificationCode(
      String email,
      String gamePackageName,
      String env,
      String marketName) {
    email = toLowerCase(email);
    User user = securityRepository.getByEmailInAllMarket(
        email,
        gamePackageName,
        env);

    if (user == null) {
      throw new UserDoseNotExistedException();
    }
    String verificationCode = stringService.generateRandomString(true, true, 6);

    securityRepository.regenerateVerificationCode(
        email,
        verificationCode,
        timeService.getNowUnixFromInstantClass()
    );

    try {
      emailService.sendEmail(
          contextService.getEmailFrom(),
          email,
          "فعال سازی حساب کاربری",
          getContentForm(verificationCode, "verification", env, gamePackageName)

      );
    } catch (Exception ignored) {
    }
  }

  @Override
  public void changePassword(
      String email,
      String gamePackageName,
      String env,
      String marketName,
      String password,
      String newPassword) {
    email = toLowerCase(email);
    User user = securityRepository.getByEmailInAllMarket(
        email,
        gamePackageName,
        env);

    if (user == null) {
      throw new UserDoseNotExistedException();
    }
    String userPass = stringService.decodeBase64WithSalt(user.getPassword());
    if (!userPass.equals(password)) {
      throw new PasswordException();
    }
    newPassword = stringService.encodeBase64WithSalt(newPassword);

    securityRepository.changePassword(email, newPassword);
  }

  @Override
  public void forgetPassword(
      String email,
      String gamePackageName,
      String env,
      String marketName,
      String password,
      String forgetPasswordToken) {
    email = toLowerCase(email);
    User user = securityRepository.getByEmailInAllMarket(
        email,
        gamePackageName,
        env);
    if (user == null) {
      throw new UserDoseNotExistedException();
    }

    if (!user.getForgetPassToken().equals(forgetPasswordToken)) {
      throw new ForgetPassTokenException();
    }

    long nowTime = timeService.getNowUnixFromInstantClass();

    if (nowTime - user.getCreatedForgetPassTokenDate() >= 3600) {
      throw new ForgetPassTokenExpiredException();
    }
    securityRepository.changePassword(
        email,
        stringService.encodeBase64WithSalt(password));

  }

  @Transactional
  @Override
  public void sendForgetPassToken(
      String email,
      String gamePackageName,
      String env,
      String marketName) {
    email = toLowerCase(email);
    if (securityRepository.getByEmailInAllMarket(
        email,
        gamePackageName,
        env) == null) {
      throw new UserDoseNotExistedException();
    }

    String forgetPassToken = stringService.generateRandomString(true, true, 6);

    try {
      emailService.sendEmail(
          contextService.getEmailFrom(),
          email,
          "بازیابی حساب کاربری ",
          getContentForm(forgetPassToken, "forgetPass", env, gamePackageName)

      );
    } catch (Exception ignored) {
    }

    securityRepository.sendForgetPassToken(
        email,
        timeService.getNowUnixFromInstantClass(),
        forgetPassToken);
  }

  private String getContentForm(
      String token,
      String name,
      String env,
      String gamePackageName) {
    EmailForm emailForm = emailFormRepository.find(gamePackageName, env, name);
    Context context = new Context();
    context.setVariable("change_pss_api", token);
    return templateEngine.process(emailForm.getFilePath(), context);
  }

  private String toLowerCase(String srt) {
    return srt.toLowerCase();
  }
}
