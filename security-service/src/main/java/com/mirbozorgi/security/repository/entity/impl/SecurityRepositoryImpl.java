package com.mirbozorgi.security.repository.entity.impl;

import com.mirbozorgi.security.entity.User;
import com.mirbozorgi.security.repository.entity.SecurityRepository;
import com.mirbozorgi.security.constant.EnumRole;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class SecurityRepositoryImpl extends CustomRepository implements SecurityRepository {

  @Override
  public User register(User user) {
    return save(User.class, user);
  }

  @Override
  public boolean updateGuestToUser(
      String uuid,
      String password,
      String email,
      String globalUniqueId,
      String deviceId,
      EnumRole role,
      String verificationCode,
      String sessionId,
      String gamePackageName,
      String env) {
    EnumRole guest = EnumRole.GUEST_USER;
    int i = entityManager.createQuery("update User set"
        + " password = :password ,"
        + " email = :email ,"
        + " globalUniqueId = :globalUniqueId ,"
        + " verificationCode = :verificationCode ,"
        + " sessionId = :sessionId ,"
        + " role = :role ,"
        + " deviceId = :deviceId "
        + "Where uuid = :uuid "
        + "and gamePackageName =: gamePackageName "
        + "and role =: guest "
        + "and env =: env ")

        .setParameter("uuid", uuid)
        .setParameter("gamePackageName", gamePackageName)
        .setParameter("env", env)
        .setParameter("guest", guest)
        .setParameter("password", password)
        .setParameter("email", email)
        .setParameter("globalUniqueId", globalUniqueId)
        .setParameter("verificationCode", verificationCode)
        .setParameter("sessionId", sessionId)
        .setParameter("role", role)
        .setParameter("deviceId", deviceId)
        .executeUpdate();

    return i > 0;

  }

  @Override
  public List<User> get(
      String email,
      String deviceId,
      String gamePackageName,
      String env) {

    if (!email.isEmpty()) {
      return listQueryWrapper(entityManager
          .createQuery("select u from User u where u.email= :email "
                  + "and u.gamePackageName= :gamePackageName "
                  + "and u.env= :env ",
              User.class)
          .setParameter("email", email)
          .setParameter("gamePackageName", gamePackageName)
          .setParameter("env", env)

      );
    } else if (!deviceId.isEmpty()) {
      return listQueryWrapper(entityManager
          .createQuery("select u from User u where u.deviceId= :deviceId "
                  + "and u.gamePackageName= :gamePackageName "
                  + "and u.env= :env ",
              User.class)
          .setParameter("deviceId", deviceId)
          .setParameter("gamePackageName", gamePackageName)
          .setParameter("env", env)

      );
    }
    return new ArrayList<>();
  }

  @Override
  public List<User> getWithRoleAndDeviceAndEmail(
      String email,
      String deviceId,
      String gamePackageName,
      String env,
      EnumRole role) {

    if (!email.isEmpty()) {
      return listQueryWrapper(entityManager
          .createQuery("select u from User u where u.email= :email "
                  + "and u.gamePackageName= :gamePackageName "
                  + "and u.role= :role "
                  + "and u.env= :env ",
              User.class)
          .setParameter("email", email)
          .setParameter("gamePackageName", gamePackageName)
          .setParameter("role", role)
          .setParameter("env", env)

      );
    } else if (!deviceId.isEmpty()) {
      return listQueryWrapper(entityManager
          .createQuery("select u from User u where u.deviceId= :deviceId "
                  + "and u.gamePackageName= :gamePackageName "
                  + "and u.role= :role "
                  + "and u.env= :env ",
              User.class)
          .setParameter("deviceId", deviceId)
          .setParameter("role", role)
          .setParameter("gamePackageName", gamePackageName)
          .setParameter("env", env)

      );
    }
    return new ArrayList<>();
  }

  @Override
  public String getDeviceId(String uuid,
      String gamePackageName,
      String env,
      String market) {
    User user = findQueryWrapper(entityManager
        .createQuery("select u from User u where u.uuid= :uuid "
                + "and u.gamePackageName= :gamePackageName "
                + "and u.market= :market "
                + "and u.env= :env",
            User.class)
        .setParameter("uuid", uuid)
        .setParameter("gamePackageName", gamePackageName)
        .setParameter("env", env)
        .setParameter("market", market));
    return user.getDeviceId();
  }

  @Override
  public void updateSessionId(
      String uuid,
      String gamePackageName,
      String market,
      String env,
      String sessionId) {
    entityManager.createQuery("update User set"
        + " sessionId = :sessionId "
        + " Where uuid = :uuid "
        + "and gamePackageName =: gamePackageName "
        + "and market =: market "
        + "and env =: env ")
        .setParameter("sessionId", sessionId)
        .setParameter("uuid", uuid)
        .setParameter("env", env)
        .setParameter("market", market)
        .setParameter("gamePackageName", gamePackageName)
        .executeUpdate();

  }

  @Override
  public void updateEmail(String deviceId,
      String gamePackageName,
      String env,
      String market,
      String email,
      Boolean verifyEmail) {
    int i = entityManager.createQuery("update User set"
        + " email = :email"
        + " Where deviceId = :deviceId "
        + "and gamePackageName =: gamePackageName "
        + "and verifyEmail =: verifyEmail "
        + "and market =: market "
        + "and env =: env "

    )
        .setParameter("email", email)
        .setParameter("deviceId", deviceId)
        .setParameter("env", env)
        .setParameter("market", market)
        .setParameter("gamePackageName", gamePackageName)
        .setParameter("verifyEmail", verifyEmail)
        .executeUpdate();
  }

  @Override
  public boolean updateGlobalAndDeviceId(
      String email,
      String globalUniqueId,
      String deviceId,
      String gamePackageName,
      String env,
      String market) {
    int i = entityManager.createQuery("update User set"
        + " globalUniqueId = :globalUniqueId ,"
        + " deviceId = :deviceId "
        + " Where email = :email "
        + "and gamePackageName =: gamePackageName "
        + "and market =: market "
        + "and env =: env "

    )
        .setParameter("email", email)
        .setParameter("globalUniqueId", globalUniqueId)
        .setParameter("deviceId", deviceId)
        .setParameter("env", env)
        .setParameter("market", market)
        .setParameter("gamePackageName", gamePackageName)
        .executeUpdate();

    return i > 0;
  }

  @Transactional
  @Override
  public void verify(String email, Boolean verifyEmail, long verifiedDate) {
    entityManager.createQuery("update User set"
        + " verifyEmail = :verifyEmail ,"
        + " verificationCode = :verificationCode ,"
        + " verifiedDate = :verifiedDate "
        + " Where email = :email")
        .setParameter("email", email)
        .setParameter("verifyEmail", verifyEmail)
        .setParameter("verificationCode", "")
        .setParameter("verifiedDate", verifiedDate)
        .executeUpdate();

  }

  @Transactional
  @Override
  public void changePassword(String email, String password) {
    entityManager.createQuery("update User set"
        + " password = :password "
        + " Where email = :email")
        .setParameter("email", email)
        .setParameter("password", password)
        .executeUpdate();

  }

  @Override
  public void regenerateVerificationCode(String email, String verificationCode,
      long verificationCodeCreatedDate) {
    entityManager.createQuery("update User set"
        + " verificationCode = :verificationCode,"
        + " verificationCodeCreatedDate = :verificationCodeCreatedDate, "
        + " verifyEmail = false "
        + " Where email = :email")
        .setParameter("email", email)
        .setParameter("verificationCode", verificationCode)
        .setParameter("verificationCodeCreatedDate", verificationCodeCreatedDate)
        .executeUpdate();
  }

  @Override
  public void sendForgetPassToken(
      String email,
      long createdForgetPassTokenDate,
      String forgetPassToken) {
    entityManager.createQuery("update User set"
        + " forgetPassToken = :forgetPassToken, "
        + " createdForgetPassTokenDate = :createdForgetPassTokenDate "
        + " Where email = :email")
        .setParameter("email", email)
        .setParameter("createdForgetPassTokenDate", createdForgetPassTokenDate)
        .setParameter("forgetPassToken", forgetPassToken)
        .executeUpdate();
  }

  @Override
  public User getByEmail(
      String email,
      String gamePackageName,
      String env,
      String market) {
    return findQueryWrapper(entityManager
        .createQuery("select u from User u where u.email= :email "
                + "and u.gamePackageName= :gamePackageName "
                + "and u.market= :market "
                + "and u.env= :env ",
            User.class)
        .setParameter("email", email)
        .setParameter("gamePackageName", gamePackageName)
        .setParameter("env", env)
        .setParameter("market", market)

    );

  }

  @Override
  public User getByEmailInAllMarket(
      String email,
      String gamePackageName,
      String env) {
    return findQueryWrapper(entityManager
        .createQuery("select u from User u where u.email= :email "
                + "and u.gamePackageName= :gamePackageName "
                + "and u.env= :env ",
            User.class)
        .setParameter("email", email)
        .setParameter("gamePackageName", gamePackageName)
        .setParameter("env", env)

    );
  }

  @Override
  public User getByUuId(String uuid,
      String gamePackageName,
      String market,
      String env) {
    return findQueryWrapper(entityManager
        .createQuery("select u from User u where u.uuid= :uuid "
                + "and u.gamePackageName= :gamePackageName "
                + "and u.market= :market "
                + "and u.env= :env ",
            User.class)
        .setParameter("uuid", uuid)
        .setParameter("gamePackageName", gamePackageName)
        .setParameter("env", env)
        .setParameter("market", market));

  }


  @Override
  public User getByRoleAndDeviceId(
      EnumRole role,
      String deviceId,
      String gamePackageName,
      String env,
      String market) {
    return findQueryWrapper(entityManager
        .createQuery("select u from User u where"
                + " u.deviceId= :deviceId"
                + " and u.gamePackageName= :gamePackageName"
                + " and u.env= :env"
                + " and u.market= :market"
                + " and u.role= :role",
            User.class)
        .setParameter("deviceId", deviceId)
        .setParameter("role", role)
        .setParameter("market", market)
        .setParameter("env", env)
        .setParameter("gamePackageName", gamePackageName)
    );
  }

  @Override
  public User getByRoleAndDeviceIdWithEmailAndNotVerified(
      EnumRole role,
      Boolean verifyEmail,
      String deviceId,
      String gamePackageName,
      String env,
      String market) {
    return findQueryWrapper(entityManager
        .createQuery("select u from User u where"
                + " u.deviceId= :deviceId"
                + " and u.gamePackageName= :gamePackageName"
                + " and u.env= :env"
                + " and u.market= :market"
                + " and u.verifyEmail= :verifyEmail"
                + " and u.role= :role",
            User.class)
        .setParameter("deviceId", deviceId)
        .setParameter("role", role)
        .setParameter("market", market)
        .setParameter("verifyEmail", verifyEmail)
        .setParameter("env", env)
        .setParameter("gamePackageName", gamePackageName)
    );
  }

}
