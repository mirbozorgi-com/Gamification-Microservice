package com.mirbozorgi.security.service.impl;

import com.mirbozorgi.security.entity.User;
import java.util.HashMap;
import java.util.Map;

public class JwtWrapper {

  private Map<String, Object> map = new HashMap<>();

  public JwtWrapper(User user) {
    map.put("role", user.getRole());
    map.put("email", user.getEmail());
    map.put("globalUniqueId", user.getGlobalUniqueId());
    map.put("deviceId", user.getDeviceId());
    map.put("uuid", user.getUuid());
    map.put("sessionId", user.getSessionId());

  }

  public Map<String, Object> getMap() {
    return map;
  }
}



