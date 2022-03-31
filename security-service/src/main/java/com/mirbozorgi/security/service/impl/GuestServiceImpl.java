package com.mirbozorgi.security.service.impl;

import com.mirbozorgi.security.domain.LoginInfo;
import com.mirbozorgi.security.domain.RegisterInfo;
import com.mirbozorgi.security.entity.User;
import com.mirbozorgi.security.mapper.UserMapper;
import com.mirbozorgi.security.repository.entity.SecurityRepository;
import com.mirbozorgi.security.service.JwtService;
import com.mirbozorgi.security.constant.EnumRole;
import com.mirbozorgi.security.exception.DeviceIdEmptyException;
import com.mirbozorgi.security.service.GuestService;
import com.mirbozorgi.security.service.StringService;
import com.mirbozorgi.security.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GuestServiceImpl implements GuestService {

  @Autowired
  private SecurityRepository securityRepository;


  @Autowired
  private StringService stringService;

  @Autowired
  private JwtService jwtService;

  @Autowired
  private TimeService timeService;

  @Transactional
  @Override
  public LoginInfo login(
      String deviceId,
      String gamePackageName,
      String env,
      String marketName) {
    RegisterInfo register = null;
    if (deviceId == null || deviceId.equals("")) {
      throw new DeviceIdEmptyException();
    }
    String token = "";
    User userFounded = securityRepository.getByRoleAndDeviceIdWithEmailAndNotVerified(
        EnumRole.GUEST_USER,
        false,
        deviceId,
        gamePackageName,
        env,
        marketName);




    if (userFounded == null) {
      //if we have this user but in another marketName
      User user = null;

      try {
        user = securityRepository.getWithRoleAndDeviceAndEmail(
            "",
            deviceId,
            gamePackageName,
            env,
            EnumRole.GUEST_USER
        ).get(0);

      } catch (Exception ignored) {
      }

      if (user != null) {
        register = registerWithSpecificDeviceIdUuid(
            deviceId,
            gamePackageName,
            env,
            marketName,
            user.getUuid()
        );
        token = jwtService.createToken(
            "",
            register.getRole(),
            register.getUuid(),
            register.getGlobalUniqueId(),
            //mock the sessionId because we dont use it in guest login
            stringService.generateRandomString(true, true, 10),
            register.getDeviceId()
        );
        return new LoginInfo(token, register.getUuid());
      }

      // new user :
      register = register(
          deviceId,
          gamePackageName,
          env,
          marketName);
      token = jwtService.createToken(
          "",
          register.getRole(),
          register.getUuid(),
          register.getGlobalUniqueId(),
          //mock the sessionId because we dont use it in guest login
          stringService.generateRandomString(true, true, 10),
          register.getDeviceId()
      );
      return new LoginInfo(token, register.getUuid());

    }

    token = jwtService.createToken(
        "",
        userFounded.getRole(),
        userFounded.getUuid(),
        userFounded.getGlobalUniqueId(),
        userFounded.getSessionId(),
        userFounded.getDeviceId()
    );

    return new LoginInfo(token, userFounded.getUuid());

  }

  ////////////////////////////////
  ////////////private method////////
  //////////////////////////////////////

  private RegisterInfo register(
      String deviceId,
      String gamePackageName,
      String env,
      String market) {
    String uuid = stringService.generateRandomString(true, true, 15);
    String sessionId = stringService.generateRandomString(true, true, 15);
    if (deviceId.equals("")) {
      deviceId = uuid;
    }
    User user = new User(
        uuid,
        EnumRole.GUEST_USER,
        "",
        null,
        deviceId,
        deviceId,
        false,
        "",
        "",
        0,
        0,
        0,
        timeService.getNowUnixFromInstantClass(),
        sessionId,
        gamePackageName,
        env,
        market
    );

    return UserMapper.toRegisterInfo(securityRepository.register(user));
  }


  private RegisterInfo registerWithSpecificDeviceIdUuid(
      String deviceId,
      String gamePackageName,
      String env,
      String market,
      String uuid) {
    String sessionId = stringService.generateRandomString(true, true, 15);
    if (deviceId.equals("")) {
      deviceId = uuid;
    }
    User user = new User(
        uuid,
        EnumRole.GUEST_USER,
        "",
        null,
        deviceId,
        deviceId,
        false,
        "",
        "",
        0,
        0,
        0,
        timeService.getNowUnixFromInstantClass(),
        sessionId,
        gamePackageName,
        env,
        market
    );

    return UserMapper.toRegisterInfo(securityRepository.register(user));
  }
}
