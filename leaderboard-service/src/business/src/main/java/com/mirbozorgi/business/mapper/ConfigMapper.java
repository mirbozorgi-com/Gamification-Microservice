package com.mirbozorgi.business.mapper;

import com.mirbozorgi.business.domain.ConfigDataResponse;
import com.mirbozorgi.core.entity.Config;

public class ConfigMapper {

  public static ConfigDataResponse toResponse(Config config) {
    return new ConfigDataResponse(
        config.getConfigData().getXpResetInWeekCron(),
        config.getConfigData().getReferalResetDayInWeekCron(),
        config.getConfigData().getGemResetDayInWeekCron()
    );
  }

}
