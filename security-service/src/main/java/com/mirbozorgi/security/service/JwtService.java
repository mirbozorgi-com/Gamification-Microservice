package com.mirbozorgi.security.service;


import com.mirbozorgi.security.domain.AuthorizationInfo;
import com.mirbozorgi.security.constant.EnumRole;
import java.util.Date;
import java.util.List;

public interface JwtService {

  String createToken(
      String email,
      EnumRole role,
      String uuid,
      String globalUniqueId,
      String sessionId,
      String deviceId);

  List<Object> parseToken(String token, String secretPass);

  boolean validateToken(String token, String secret);

  AuthorizationInfo authorize(String token);

  Date getIssuedAt(String token);

  Date getExpiration(String token);

}

