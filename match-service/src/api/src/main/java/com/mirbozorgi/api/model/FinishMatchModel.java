package com.mirbozorgi.api.model;

import java.util.List;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

public class FinishMatchModel {

  @NotBlank
  @NotNull
  private String matchId;
  @NotNull
  private Boolean win;
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

  public String getMatchId() {
    return matchId;
  }

  public Boolean getWin() {
    return win;
  }
}
