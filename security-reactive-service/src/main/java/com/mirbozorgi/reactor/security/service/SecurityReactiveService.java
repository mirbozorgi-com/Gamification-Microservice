package com.mirbozorgi.reactor.security.service;

import com.mirbozorgi.reactor.security.domain.AuthorizationInfo;

public interface SecurityReactiveService {


  AuthorizationInfo authorize(String token);
}
