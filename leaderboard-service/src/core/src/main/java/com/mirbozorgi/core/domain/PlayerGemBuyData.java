package com.mirbozorgi.core.domain;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;

public class PlayerGemBuyData {


  @Indexed(direction = IndexDirection.DESCENDING)
  private int gem;
  private long date;
  private int level;
  private String username;
  private List<Integer> avatarActiveIds;
  private long endVipTime;

  public PlayerGemBuyData(
      int gem,
      long date,
      String username,
      List<Integer> avatarActiveIds,
      int level,
      long endVipTime) {
    this.gem = gem;
    this.date = date;
    this.level = level;
    this.username = username;
    this.avatarActiveIds = avatarActiveIds;
    this.endVipTime = endVipTime;
  }

  public PlayerGemBuyData() {
    avatarActiveIds = new ArrayList<>();
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

  public long getDate() {
    return date;
  }

  public int getGem() {
    return gem;
  }


  public String getUsername() {
    return username;
  }
}
