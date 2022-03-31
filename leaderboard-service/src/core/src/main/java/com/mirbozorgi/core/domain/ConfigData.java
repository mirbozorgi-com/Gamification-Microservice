package com.mirbozorgi.core.domain;

public class ConfigData {

  private int xpResetInWeekCron;

  private int referalResetDayInWeekCron;

  private int gemResetDayInWeekCron;

  public ConfigData(int xpResetInWeekCron, int referalResetDayInWeekCron,
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
