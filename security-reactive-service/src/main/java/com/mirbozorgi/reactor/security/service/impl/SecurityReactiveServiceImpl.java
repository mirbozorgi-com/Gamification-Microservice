package com.mirbozorgi.reactor.security.service.impl;

import com.mirbozorgi.reactor.security.constant.EnumRole;
import com.mirbozorgi.reactor.security.domain.AuthorizationInfo;
import com.mirbozorgi.reactor.security.entity.User;
import com.mirbozorgi.reactor.security.exception.SessionIdException;
import com.mirbozorgi.reactor.security.repository.UserRepository;
import com.mirbozorgi.reactor.security.repository.memory.PlayerSessionRepository;
import com.mirbozorgi.reactor.security.service.JwtService;
import com.mirbozorgi.reactor.security.service.SecurityReactiveService;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecurityReactiveServiceImpl implements SecurityReactiveService {

  @Autowired
  private UserRepository securityRepository;

  @Autowired
  private JwtService jwtService;

  @Autowired
  private PlayerSessionRepository playerSessionRepository;


  @Override
  public AuthorizationInfo authorize(String token) {
    AuthorizationInfo authorize = jwtService.authorize(token);
    if (!authorize.getRole().equals(EnumRole.GUEST_USER.toString())) {
      String sessionId = playerSessionRepository.get(authorize.getUuid());
      if (sessionId == null) {
        Optional<User> user = securityRepository.findByUuid(authorize.getUuid())
            .map(Optional::of)
            .onErrorReturn(Optional.empty()).block();

        assert Objects.requireNonNull(user).isPresent();
        sessionId = Objects.requireNonNull(user.get()).getSessionId();
        playerSessionRepository.add(user.get().getUuid(), sessionId);
      }
      if (!authorize.getSessionId().equals(sessionId)) {
        throw new SessionIdException();
      }
    }

    return authorize;
  }
}
