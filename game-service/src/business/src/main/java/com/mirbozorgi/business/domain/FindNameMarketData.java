package com.mirbozorgi.business.domain;

public class FindNameMarketData {

  private String name;

  private int lastVersion;

  private int supportedVersion;

  public FindNameMarketData(String name, int lastVersion, int supportedVersion) {
    this.name = name;
    this.lastVersion = lastVersion;
    this.supportedVersion = supportedVersion;
  }

  public String getName() {
    return name;
  }

  public int getLastVersion() {
    return lastVersion;
  }

  public int getSupportedVersion() {
    return supportedVersion;
  }
}
