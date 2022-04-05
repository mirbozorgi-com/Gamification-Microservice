package com.mirbozorgi.core.domain;

import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;

public class PlayerGameScore {


  @Indexed(direction = IndexDirection.DESCENDING)
  private int score;

  private long lastUpdateScoreDate;

  private String username;


  public PlayerGameScore(int score, long lastUpdateScoreDate, String username) {
    this.score = score;
    this.lastUpdateScoreDate = lastUpdateScoreDate;
    this.username = username;
  }

  public PlayerGameScore() {
  }

  public String getUsername() {
    return username;
  }

  public int getScore() {
    return score;
  }

  public long getLastUpdateScoreDate() {
    return lastUpdateScoreDate;
  }
}
