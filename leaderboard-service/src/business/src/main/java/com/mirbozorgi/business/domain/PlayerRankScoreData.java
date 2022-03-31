package com.mirbozorgi.business.domain;

public class PlayerRankScoreData {

  private String userUuId;
  private String username;
  private int rank;
  private int score;

  public PlayerRankScoreData(String userUuId, String username, int rank, int score) {
    this.userUuId = userUuId;
    this.username = username;
    this.rank = rank;
    this.score = score;
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
