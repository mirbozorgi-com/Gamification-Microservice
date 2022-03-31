package com.mirbozorgi.business.domain;

import com.mirbozorgi.business.constant.UpdateStatus;

public class TimeAndVersioningInfo {

  private long serverTimeUtc;

  private UpdateStatus status;

  public TimeAndVersioningInfo(
      long serverTimeUtc,
      UpdateStatus status) {
    this.serverTimeUtc = serverTimeUtc;
    this.status = status;
  }

  public long getServerTimeUtc() {
    return serverTimeUtc;
  }

  public UpdateStatus getStatus() {
    return status;
  }
}
