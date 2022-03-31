package com.mirbozorgi.api.model;

import com.mirbozorgi.core.domain.LeaderBoardType;
import javax.validation.constraints.NotNull;

public class ResetModel {

  @NotNull
  private LeaderBoardType type;

  public LeaderBoardType getType() {
    return type;
  }
}
