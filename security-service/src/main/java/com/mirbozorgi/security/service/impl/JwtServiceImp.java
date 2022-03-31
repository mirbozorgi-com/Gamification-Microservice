package com.mirbozorgi.security.service.impl;

import com.mirbozorgi.security.constant.EnumRole;
import com.mirbozorgi.security.domain.AuthorizationInfo;
import com.mirbozorgi.security.entity.User;
import com.mirbozorgi.security.exception.AccessDeniedException;
import com.mirbozorgi.security.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Clock;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClock;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class JwtServiceImp implements JwtService {

  private Clock clock = DefaultClock.INSTANCE;
  @Autowired
  private Environment env;

  @Value("${jwt.server.secret}")
  private String secret;
  @Value("${jwt.expiration}")
  private int expiration;

  @Override
  public String createToken(
      String email,
      EnumRole role,
      String uuid,
      String globalUniqueId,
      String sessionId,
      String deviceId) {

    Date createdDate = clock.now();
    Date expirationDate = calculateExpirationDate(createdDate,
        Integer.parseInt(env.getProperty("jwt.expiration")));
    return doGenerateToken(
        email,
        createdDate,
        expirationDate,
        secret,
        new JwtWrapper(new User(uuid, role, email, globalUniqueId, deviceId, sessionId)).getMap(),
        role
    );


  }

  @Override
  public List<Object> parseToken(String token, String secretPass) {
    List<Object> objects = new ArrayList<>();
    if (!this.validateToken(token, secretPass)) {
      throw new AccessDeniedException();
    }
    Claims body = Jwts.parser()
        .setSigningKey(secretPass)
        .parseClaimsJws(token.replace(env.getProperty("token.prefix"), ""))
        .getBody();
    Object role = body.get("role");
    Object email = body.get("email");
    Object uuid = body.get("uuid");
    Object deviceId = body.get("deviceId");
    Object globalUniqueId = body.get("globalUniqueId");
    Object sessionId = body.get("sessionId");
    objects.add(role);
    objects.add(email);
    objects.add(uuid);
    objects.add(deviceId);
    objects.add(globalUniqueId);
    objects.add(sessionId);
    return objects;
  }

  @Override
  public boolean validateToken(String token, String secret) {
    Claims claims = null;
    try {
      claims = Jwts.parser()
          .setSigningKey(secret)
          .parseClaimsJws(token.replace(env.getProperty("token.prefix"), ""))
          .getBody();
    } catch (Exception e) {
      throw new AccessDeniedException();
    }
    if (claims == null) {
      return false;
    }
    return true;
  }


  @Override
  public AuthorizationInfo authorize(String token) {
    List<Object> objects = parseToken(token, secret);
    Object role = objects.get(0);
    Object email = objects.get(1);
    Object uuid = objects.get(2);
    Object sessionId = objects.get(3);
    Object globalUniqueId = objects.get(4);
    Object deviceId = objects.get(5);
    return new AuthorizationInfo(
        role,
        email,
        uuid,
        deviceId,
        globalUniqueId,
        sessionId
    );
  }

  @Override
  public Date getIssuedAt(String token) {
    return getClaimFromToken(token, Claims::getIssuedAt);
  }

  @Override
  public Date getExpiration(String token) {
    return getClaimFromToken(token, Claims::getExpiration);

  }

  ////////private method ///////////////
  private Claims getAllClaimsFromToken(String token) {
    return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
  }


  private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = getAllClaimsFromToken(token);
    return claimsResolver.apply(claims);
  }

  private Date calculateExpirationDate(Date createdDate, int expiration) {
    return new Date(createdDate.getTime() + expiration * 1000);
  }

  private String doGenerateToken(
      String subject,
      Date createdDate,
      Date expirationDate,
      String secret,
      Map<String, Object> map,
      EnumRole role) {
    JwtBuilder builder = Jwts.builder();
    if (subject != null) {
      builder.setSubject(subject);
    }
    if (createdDate != null) {
      builder.setIssuedAt(createdDate);
    }
    if (expirationDate != null) {
      builder.setExpiration(expirationDate);
    }
    if (map != null) {
      builder.setClaims(map);
    }
    if (role.equals(EnumRole.GUEST_USER)) {
      return builder.setClaims(map)
          .signWith(SignatureAlgorithm.HS512, secret).compact();
    }
    return builder.setExpiration(expirationDate).setClaims(map)
        .signWith(SignatureAlgorithm.HS512, secret).compact();
  }


}
