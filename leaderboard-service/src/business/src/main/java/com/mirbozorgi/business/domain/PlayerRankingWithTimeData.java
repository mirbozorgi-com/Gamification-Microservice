package com.mirbozorgi.business.domain;

import java.util.List;

public class PlayerRankingWithTimeData {

  private List<?> ranking;

  private long endTime;

  private int ownScore;

  public PlayerRankingWithTimeData(
      List<?> ranking,
      long endTime,
      int ownScore) {
    this.ranking = ranking;
    this.endTime = endTime;
    this.ownScore = ownScore;
  }

  public int getOwnScore() {
    return ownScore;
  }

  public List<?> getRanking() {
    return ranking;
  }

  public long getEndTime() {
    return endTime;
  }
}
