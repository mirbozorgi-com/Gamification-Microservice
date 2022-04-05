package com.mirbozorgi.api.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class OfflineUpdateModel {


  @NotBlank
  private String id;
  @NotNull
  private Boolean active;
  @NotNull
  private Boolean win;

  public Boolean getWin() {
    return win;
  }

  public String getId() {
    return id;
  }

  public Boolean getActive() {
    return active;
  }
}
