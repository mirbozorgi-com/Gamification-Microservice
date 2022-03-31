package com.mirbozorgi.core.domain;

import java.util.List;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;

public class PlayerRefralData {

  @Indexed(direction = IndexDirection.DESCENDING)
  private int quantity;
  private int level;
  private String username;
  private List<Integer> avatarActiveIds;
  private long endVipTime;

  public PlayerRefralData(
      int quantity,
      String username,
      List<Integer> avatarActiveIds,
      int level,
      long endVipTime) {
    this.quantity = quantity;
    this.level = level;
    this.username = username;
    this.avatarActiveIds = avatarActiveIds;
    this.endVipTime = endVipTime;
  }

  public PlayerRefralData() {
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

  public int getQuantity() {
    return quantity;
  }

  public String getUsername() {
    return username;
  }
}
