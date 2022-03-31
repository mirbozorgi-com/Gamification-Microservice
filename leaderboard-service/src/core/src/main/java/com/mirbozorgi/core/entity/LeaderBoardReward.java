package com.mirbozorgi.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mirbozorgi.core.domain.LeaderBoardType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mirbozorgi.base.domain.user.WalletChange;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "leader_board_reward")
@CompoundIndex(
    name = "UK_LEADER_BOARD_REWARD_GAME_PACKAGE_NAME_MARKET_NAME_ENV",
    unique = true,
    def = "{'gamePackageName':1, 'marketName':1 , 'env':1}")
public class LeaderBoardReward {

  @Id
  private ObjectId id;

  private String gamePackageName;

  private String env;

  private String marketName;

  //key :type
  private Map<LeaderBoardType, List<WalletChange>> rewards;


  public LeaderBoardReward() {
    rewards = new HashMap<>();
  }

  public LeaderBoardReward(
      String gamePackageName,
      String env,
      String marketName,
      Map<LeaderBoardType, List<WalletChange>> rewards) {
    this.gamePackageName = gamePackageName;
    this.env = env;
    this.marketName = marketName;
    this.rewards = rewards;
  }

  public ObjectId getId() {
    return id;
  }

  public String getGamePackageName() {
    return gamePackageName;
  }

  public String getEnv() {
    return env;
  }

  public String getMarketName() {
    return marketName;
  }

  public Map<LeaderBoardType, List<WalletChange>> getRewards() {
    return rewards;
  }

  @JsonIgnore
  public String getStringId() {
    return id.toString();
  }
}
