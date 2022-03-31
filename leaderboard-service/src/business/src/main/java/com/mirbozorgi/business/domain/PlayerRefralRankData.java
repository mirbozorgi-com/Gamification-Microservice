package com.mirbozorgi.business.domain;

import java.util.List;

public class PlayerRefralRankData {

  private String userUuId;
  private String username;
  private int rank;
  private int score;
  private int level;
  private List<Integer> avatarActiveIds;
  private long endVipTime;

  public PlayerRefralRankData(
      String userUuId,
      String username,
      int rank,
      int score,
      int level,
      List<Integer> avatarActiveIds,
      long endVipTime) {
    this.userUuId = userUuId;
    this.level = level;
    this.username = username;
    this.rank = rank;
    this.score = score;
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

  public String getUserUuId() {
    return userUuId;
  }

  public String getUsername() {
    return username;
  }

  public int getRank() {
    return rank;
  }

  public int getScore() {
    return score;
  }
}
