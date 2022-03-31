package com.mirbozorgi.core.document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user_challenge")
public class UserChallenge {

  @Id
  private ObjectId id;

  private String challengeId;

  private String userUuId;

  private String username;

  private String name;

  private String gamePackageName;

  private String env;

  private String marketName;

  @Indexed(direction = IndexDirection.DESCENDING)
  private int score;

  private long lastUpdateScoreTime;

  private long cheatUpdateRequest;

  private Boolean claim;

  private Boolean banned;

  private long endTime;

  public UserChallenge(String challengeId, String name, String gamePackageName, String env,
      String marketName, String userUuId, int score, long lastUpdateScoreTime,
      long cheatUpdateRequest, Boolean claim, Boolean banned, long endTime, String username) {
    this.challengeId = challengeId;
    this.name = name;
    this.gamePackageName = gamePackageName;
    this.env = env;
    this.marketName = marketName;
    this.userUuId = userUuId;
    this.score = score;
    this.lastUpdateScoreTime = lastUpdateScoreTime;
    this.cheatUpdateRequest = cheatUpdateRequest;
    this.claim = claim;
    this.banned = banned;
    this.endTime = endTime;
    this.username = username;
  }

  public UserChallenge() {
  }

  public String getUsername() {
    return username;
  }

  public ObjectId getId() {
    return id;
  }

  public String getChallengeId() {
    return challengeId;
  }

  public String getName() {
    return name;
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

  public String getUserUuId() {
    return userUuId;
  }

  public int getScore() {
    return score;
  }

  public long getLastUpdateScoreTime() {
    return lastUpdateScoreTime;
  }

  public long getCheatUpdateRequest() {
    return cheatUpdateRequest;
  }

  public Boolean getClaim() {
    return claim;
  }

  public Boolean getBanned() {
    return banned;
  }

  public long getEndTime() {
    return endTime;
  }

  @JsonIgnore
  public String getStringId() {
    return id.toString();
  }
}
