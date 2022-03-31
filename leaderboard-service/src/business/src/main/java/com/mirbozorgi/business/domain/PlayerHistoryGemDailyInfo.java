package com.mirbozorgi.business.domain;

import java.util.List;

public class PlayerHistoryGemDailyInfo {

  private List<PlayerRankGemBuyHistoryData> playerRankGemBuyHistoryData;
  private long ownDate;
  private int ownScore;

  public PlayerHistoryGemDailyInfo(
      List<PlayerRankGemBuyHistoryData> playerRankGemBuyHistoryData, long ownDate, int ownScore) {
    this.playerRankGemBuyHistoryData = playerRankGemBuyHistoryData;
    this.ownDate = ownDate;
    this.ownScore = ownScore;
  }

  public List<PlayerRankGemBuyHistoryData> getPlayerRankGemBuyHistoryData() {
    return playerRankGemBuyHistoryData;
  }

  public long getOwnDate() {
    return ownDate;
  }

  public int getOwnScore() {
    return ownScore;
  }
}
