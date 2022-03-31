package com.mirbozorgi.core.document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mirbozorgi.core.domain.SocialNetWorkFollowData;
import java.util.HashMap;
import java.util.Map;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "social_networks_follow_reward")

@CompoundIndex(
    name = "UK_DAILY_REWARD_CONTINUES_PACKAGE_NAME_ENV",
    unique = true,
    def = "{'gameId':1,'gamePackageName':1,'env':1}")
public class SocialNetWorksFollowReward {

  @Id
  private ObjectId id;

  private int gameId;

  private String gamePackageName;

  private String env;

  private Map<String, SocialNetWorkFollowData> rewards;

  public SocialNetWorksFollowReward() {
    rewards = new HashMap<>();
  }

  public SocialNetWorksFollowReward(
      int gameId,
      String gamePackageName,
      String env,
      Map<String, SocialNetWorkFollowData> rewards) {
    this.gameId = gameId;
    this.gamePackageName = gamePackageName;
    this.env = env;
    this.rewards = rewards;
  }

  public ObjectId getId() {
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

  @JsonIgnore
  public String getStringId() {
    return id.toString();
  }
}
