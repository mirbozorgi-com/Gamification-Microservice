package com.mirbozorgi.core.domain;

import java.util.List;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;

public class PlayerXpData {

  @Indexed(direction = IndexDirection.DESCENDING)
  private int xp;
  private long lastUpdate;
  private String username;
  private int level;
  private List<Integer> avatarActiveIds;
  private long endVipTime;

  public PlayerXpData(
      int xp,
      long lastUpdate,
      String username,
      List<Integer> avatarActiveIds,
      int level,
      long endVipTime) {
    this.xp = xp;
    this.lastUpdate = lastUpdate;
    this.username = username;
    this.avatarActiveIds = avatarActiveIds;
    this.level = level;
    this.endVipTime = endVipTime;
  }

  public PlayerXpData() {
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

  public int getXp() {
    return xp;
  }

  public long getLastUpdate() {
    return lastUpdate;
  }

  public String getUsername() {
    return username;
  }
}
