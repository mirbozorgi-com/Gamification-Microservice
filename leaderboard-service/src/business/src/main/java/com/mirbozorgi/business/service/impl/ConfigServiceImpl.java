package com.mirbozorgi.business.service.impl;

import com.mirbozorgi.business.service.ConfigService;
import com.mirbozorgi.business.domain.ConfigDataResponse;
import com.mirbozorgi.business.mapper.ConfigMapper;
import com.mirbozorgi.core.domain.ConfigData;
import com.mirbozorgi.core.entity.Config;
import com.mirbozorgi.core.repository.document.ConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigServiceImpl implements ConfigService {

  @Autowired
  private ConfigRepository configRepository;

  @Override
  public ConfigDataResponse get(
      String gamePackageName,
      String env,
      String marketName) {

    Config config = configRepository.get(gamePackageName, env, marketName);

    return ConfigMapper.toResponse(config);
  }

  @Override
  public ConfigDataResponse upsert(
      String gamePackageName,
      String env,
      String marketName,
      int xpResetInWeekCron,
      int referalResetDayInWeekCron,
      int gemResetDayInWeekCron) {

    ConfigData configData = new ConfigData(
        xpResetInWeekCron,
        referalResetDayInWeekCron,
        gemResetDayInWeekCron
    );

    Config upsert = configRepository.upsert(
        gamePackageName,
        env,
        marketName,
        configData
    );

    return ConfigMapper.toResponse(upsert);

  }
}
