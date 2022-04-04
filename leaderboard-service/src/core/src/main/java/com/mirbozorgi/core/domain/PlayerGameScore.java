package com.mirbozorgi.core.domain;

import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;

public class Playerarsalancore {


  @Indexed(direction = IndexDirection.DESCENDING)
  private int score;

  private long lastUpdateScoreDate;

  private String username;


  public Playerarsalancore(int score, long lastUpdateScoreDate, String username) {
    this.score = score;
    this.lastUpdateScoreDate = lastUpdateScoreDate;
    this.username = username;
  }

  public Playerarsalancore() {
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
