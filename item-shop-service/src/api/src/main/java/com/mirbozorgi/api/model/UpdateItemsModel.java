package com.mirbozorgi.api.model;

import com.mirbozorgi.core.domain.ItemData;
import java.util.List;

public class UpdateItemsModel {

  private String key;
  private List<ItemData> itemDatas;

  public String getKey() {
    return key;
  }

  public List<ItemData> getItemDatas() {
    return itemDatas;
  }
}
