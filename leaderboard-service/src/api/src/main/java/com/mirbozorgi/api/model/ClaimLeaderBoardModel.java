package com.mirbozorgi.api.model;

import com.mirbozorgi.core.domain.LeaderBoardType;
import java.util.List;

public class ClaimLeaderBoardModel {

  private LeaderBoardType type;
  private String username;
  private int currentLevel;
  private List<Integer> currentAvatarIds;
  private long endVipTime;

  public long getEndVipTime() {
    return endVipTime;
  }

  public int getCurrentLevel() {
    return currentLevel;
  }

  public List<Integer> getCurrentAvatarIds() {
    return currentAvatarIds;
  }

  public LeaderBoardType getType() {
    return type;
  }

  public String getUsername() {
    return username;
  }


}
