package com.mirbozorgi.api.model;

import com.sun.istack.NotNull;
import javax.validation.constraints.NotBlank;

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
