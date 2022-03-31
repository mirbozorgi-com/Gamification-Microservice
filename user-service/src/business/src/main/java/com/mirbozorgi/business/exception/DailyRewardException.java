package com.mirbozorgi.business.exception;

import mirbozorgi.base.exception.CustomException;
import org.springframework.http.HttpStatus;

public class DailyRewardException extends CustomException {

  public DailyRewardException() {
    super("daily_reward_is_used_for_today", HttpStatus.NOT_ACCEPTABLE);
    this.setData("daily_reward_is_used_for_today");
  }
}
