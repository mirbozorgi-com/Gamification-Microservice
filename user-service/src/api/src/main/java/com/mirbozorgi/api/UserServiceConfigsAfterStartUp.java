package com.mirbozorgi.api;

import com.mirbozorgi.core.document.Config;
import com.mirbozorgi.core.repository.document.ConfigRepository;
import java.util.HashMap;
import java.util.Map;
import mirbozorgi.base.feignService.impl.SerializerFeignServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
public class UserServiceConfigsAfterStartUp {

  @Autowired
  private ConfigRepository configRepository;

  @EventListener(ApplicationReadyEvent.class)
  public void config() {
    String s = "{\"name\":\"DEF\",\"type\":22,\"class\":\"mca\"}";
    Object o = new SerializerFeignServiceImpl().toObj(s, Object.class);
    Map<String, Object> objectMap = new HashMap<>();
    objectMap.put("DEF", o);
    configRepository
        .add(new Config("cohort", objectMap));

  }
}
