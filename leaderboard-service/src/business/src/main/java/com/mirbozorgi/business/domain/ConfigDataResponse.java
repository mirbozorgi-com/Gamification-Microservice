package com.mirbozorgi.business.domain;

public class ConfigDataResponse {

  private int xpResetInWeekCron;

  private int referalResetDayInWeekCron;

  private int gemResetDayInWeekCron;

  public ConfigDataResponse(int xpResetInWeekCron, int referalResetDayInWeekCron,
      int gemResetDayInWeekCron) {
    this.xpResetInWeekCron = xpResetInWeekCron;
    this.referalResetDayInWeekCron = referalResetDayInWeekCron;
    this.gemResetDayInWeekCron = gemResetDayInWeekCron;
  }

  public int getXpResetInWeekCron() {
    return xpResetInWeekCron;
  }

  public int getReferalResetDayInWeekCron() {
    return referalResetDayInWeekCron;
  }

  public int getGemResetDayInWeekCron() {
    return gemResetDayInWeekCron;
  }
}
