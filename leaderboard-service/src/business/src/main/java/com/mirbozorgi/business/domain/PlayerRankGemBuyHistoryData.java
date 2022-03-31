package com.mirbozorgi.business.domain;

import java.util.List;

public class PlayerRankGemBuyHistoryData {

  private String uuid;
  private String username;
  private List<Integer> avatarActiveIds;
  private int level;
  private int score;
  private long date;
  private long endVipTime;


  public PlayerRankGemBuyHistoryData(
      String uuid,
      String username,
      int score,
      int level,
      long date,
      List<Integer> avatarActiveIds,
      long endVipTime) {
    this.uuid = uuid;
    this.username = username;
    this.score = score;
    this.level = level;
    this.date = date;
    this.avatarActiveIds = avatarActiveIds;
    this.endVipTime = endVipTime;
  }

  public long getEndVipTime() {
    return endVipTime;
  }

  public int getLevel() {
    return level;
  }

  public List<Integer> getAvatarActiveIds() {
    return avatarActiveIds;
  }

  public String getUuid() {
    return uuid;
  }

  public String getUsername() {
    return username;
  }

  public int getScore() {
    return score;
  }

  public long getDate() {
    return date;
  }
}
