package com.mirbozorgi.business.service;

import com.mirbozorgi.business.domain.ConfigDataResponse;

public interface ConfigService {

  ConfigDataResponse get(
      String gamePackageName,
      String env,
      String marketName);

  ConfigDataResponse upsert(
      String gamePackageName,
      String env,
      String marketName,
      int xpResetInWeekCron,
      int referalResetDayInWeekCron,
      int gemResetDayInWeekCron);
}

