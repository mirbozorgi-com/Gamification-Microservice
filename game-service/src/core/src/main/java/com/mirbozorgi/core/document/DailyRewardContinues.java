package com.mirbozorgi.core.document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mirbozorgi.core.domain.WalletChangeModelDailyReward;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "daily_reward_continues")

@CompoundIndex(
    name = "UK_DAILY_REWARD_CONTINUES_PACKAGE_NAME_ENV",
    unique = true,
    def = "{'gameId':1,'gamePackageName':1,'env':1}"
)
public class DailyRewardContinues {

  @Id
  private ObjectId id;

  private int gameId;

  private String gamePackageName;

  private String env;

  private Map<String, List<WalletChangeModelDailyReward>> rewardFlows;

  public DailyRewardContinues() {
    rewardFlows = new HashMap<>();
  }


  public DailyRewardContinues(
      int gameId,
      String gamePackageName,
      String env,
      Map<String, List<WalletChangeModelDailyReward>> rewardFlows) {
    this.gameId = gameId;
    this.gamePackageName = gamePackageName;
    this.env = env;
    this.rewardFlows = rewardFlows;
  }

  public String getGamePackageName() {
    return gamePackageName;
  }

  public String getEnv() {
    return env;
  }


  public ObjectId getId() {
    return id;
  }

  public int getGameId() {
    return gameId;
  }

  public Map<String, List<WalletChangeModelDailyReward>> getRewardFlows() {
    return rewardFlows;
  }

  @JsonIgnore
  public String getStringId() {
    return id.toString();
  }


}
