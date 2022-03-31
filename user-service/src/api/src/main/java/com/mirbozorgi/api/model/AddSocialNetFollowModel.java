package com.mirbozorgi.api.model;

import java.util.List;
import javax.validation.constraints.NotNull;

public class AddSocialNetFollowModel {

  @NotNull
  private String username;
  @NotNull
  private String socialNetworkName;
  private List<Integer> currentAvatarIds;
  private int currentLevel;
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


  public String getUsername() {
    return username;
  }

  public String getSocialNetworkName() {
    return socialNetworkName;
  }
}
