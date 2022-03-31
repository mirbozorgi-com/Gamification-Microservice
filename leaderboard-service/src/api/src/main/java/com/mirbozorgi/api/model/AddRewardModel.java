package com.mirbozorgi.api.model;

import com.mirbozorgi.core.domain.LeaderBoardType;
import java.util.List;

public class AddRewardModel {

  private LeaderBoardType type;
  private int gem;
  private int gold;
  private String level;
  private int xp;
  private Boolean hamiAdded;
  private List<Integer> avatarIds;
  private long addedVipPeriodTime;


  public LeaderBoardType getType() {
    return type;
  }

  public int getGem() {
    return gem;
  }

  public int getGold() {
    return gold;
  }

  public String getLevel() {
    return level;
  }

  public int getXp() {
    return xp;
  }

  public Boolean getHamiAdded() {
    return hamiAdded;
  }

  public List<Integer> getAvatarIds() {
    return avatarIds;
  }

  public long getAddedVipPeriodTime() {
    return addedVipPeriodTime;
  }
}
