package com.mirbozorgi.business.domain;

import java.util.List;

public class PlayerXpRankData {

  private String userUuId;
  private String username;
  private int rank;
  private int score;
  private int level;
  private List<Integer> avatarActiveIds;
  private long endVipTime;


  public PlayerXpRankData(
      String userUuId,
      String username,
      int rank,
      int score,
      List<Integer> avatarActiveIds,
      long endVipTime,
      int level) {
    this.userUuId = userUuId;
    this.username = username;
    this.rank = rank;
    this.score = score;
    this.avatarActiveIds = avatarActiveIds;
    this.endVipTime = endVipTime;
    this.level = level;
  }

  public int getLevel() {
    return level;
  }

  public long getEndVipTime() {
    return endVipTime;
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
