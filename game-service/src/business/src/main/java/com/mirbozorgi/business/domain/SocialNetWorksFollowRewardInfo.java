package com.mirbozorgi.business.domain;

import com.mirbozorgi.core.domain.SocialNetWorkFollowData;
import java.util.Map;

public class SocialNetWorksFollowRewardInfo {

  private String id;

  private int gameId;

  private String gamePackageName;

  private String env;

  private Map<String, SocialNetWorkFollowData> rewards;

  public SocialNetWorksFollowRewardInfo(String id, int gameId, String gamePackageName,
      String env, Map<String, SocialNetWorkFollowData> rewards) {
    this.id = id;
    this.gameId = gameId;
    this.gamePackageName = gamePackageName;
    this.env = env;
    this.rewards = rewards;
  }

  public String getId() {
    return id;
  }

  public int getGameId() {
    return gameId;
  }

  public String getGamePackageName() {
    return gamePackageName;
  }

  public String getEnv() {
    return env;
  }

  public Map<String, SocialNetWorkFollowData> getRewards() {
    return rewards;
  }
}
