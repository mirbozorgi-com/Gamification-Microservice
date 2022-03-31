package com.mirbozorgi.core.docuemnt;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mirbozorgi.core.domain.SpinData;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.Id;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("spin")

@CompoundIndex(
    name = "UK_NAME_GAME_PACKAGE_NAME_ENV_MARKET",
    unique = true,
    def = "{ 'gamePackageName':1,'env':1,'market':1}")
public class Spin {

  @Id
  private ObjectId id;

  // Map keys: name
  private Map<String, SpinData> spinDatas;

  private String gamePackageName;

  private String env;

  private String market;


  public Spin() {
    this.spinDatas = new HashMap<>();
  }

  public Spin(String gamePackageName, String env, String market, Map<String, SpinData> spinDatas) {

    this.gamePackageName = gamePackageName;
    this.env = env;
    this.market = market;
    this.spinDatas = spinDatas;
  }


  public Map<String, SpinData> getSpinDatas() {
    return spinDatas;
  }

  public ObjectId getId() {
    return id;
  }

  public String getGamePackageName() {
    return gamePackageName;
  }

  public void setGamePackageName(String gamePackageName) {
    this.gamePackageName = gamePackageName;
  }

  public String getEnv() {
    return env;
  }

  public String getMarket() {
    return market;
  }


  @JsonIgnore
  public String getStringId() {
    return id.toString();
  }

}
