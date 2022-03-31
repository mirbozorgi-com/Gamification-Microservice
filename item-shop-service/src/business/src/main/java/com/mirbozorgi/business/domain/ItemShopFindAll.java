package com.mirbozorgi.business.domain;

import com.mirbozorgi.core.constant.ItemType;
import java.util.List;
import mirbozorgi.base.domain.user.WalletChange;

public class ItemShopFindAll {

  private String name;
  private Object config;
  private List<ItemType> type;
  private String fileUrl;
  private WalletChange walletChange;


  public ItemShopFindAll(String name, Object config, List<ItemType> type, String fileUrl,
      WalletChange walletChange) {
    this.name = name;
    this.config = config;
    this.type = type;
    this.fileUrl = fileUrl;
    this.walletChange = walletChange;
  }

  public String getName() {
    return name;
  }

  public Object getConfig() {
    return config;
  }

  public List<ItemType> getType() {
    return type;
  }

  public String getFileUrl() {
    return fileUrl;
  }

  public WalletChange getWalletChange() {
    return walletChange;
  }

}
