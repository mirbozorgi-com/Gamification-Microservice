package com.mirbozorgi.core.domain;

import java.util.ArrayList;
import java.util.List;

public class SpinData {

  private long timePeriod;
  private List<ItemData> itemDatas;


  public long getTimePeriod() {
    return timePeriod;
  }

  public List<ItemData> getItemDatas() {
    return itemDatas;
  }

  public SpinData(long timePeriod,
      List<ItemData> itemDatas) {
    this.timePeriod = timePeriod;
    this.itemDatas = itemDatas;
  }

  public SpinData() {
    itemDatas = new ArrayList<>();
  }

}
