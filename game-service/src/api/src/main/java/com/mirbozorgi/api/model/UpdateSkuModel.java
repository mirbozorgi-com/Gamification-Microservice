package com.mirbozorgi.api.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class UpdateSkuModel extends AddSkuModel {

  @NotNull
  @Min(0)
  private int id;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }
}
