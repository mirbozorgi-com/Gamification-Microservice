package com.mirbozorgi.security.mapper;

import com.mirbozorgi.security.domain.RegisterInfo;
import com.mirbozorgi.security.entity.User;

public class UserMapper {

  public static RegisterInfo toRegisterInfo(User user) {
    return new RegisterInfo(
        user.getUuid(),
        user.getRole(),
        user.getEmail(),
        user.getGlobalUniqueId(),
        user.getDeviceId()
    );
  }


}
