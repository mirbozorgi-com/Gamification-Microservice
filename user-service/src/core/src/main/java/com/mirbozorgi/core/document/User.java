package com.mirbozorgi.core.document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mirbozorgi.core.domain.UserGameProfile;
import java.util.HashMap;
import java.util.Map;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
public class User {

  @Id
  private ObjectId id;

  private String uuid;

  // Map keys: packageName.env.market
  private Map<String, Map<String, Map<String, UserGameProfile>>> gameProfiles;

  public User(
      String uuid,
      Map<String, Map<String, Map<String, UserGameProfile>>> gameProfiles) {
    this.gameProfiles = gameProfiles;
    this.uuid = uuid;
  }

  public User(String uuid) {
    this.uuid = uuid;
  }


  public User() {
    gameProfiles = new HashMap<>();
  }

  public ObjectId getId() {
    return id;
  }

  public String getUuid() {
    return uuid;
  }

  public Map<String, Map<String, Map<String, UserGameProfile>>> getGameProfiles() {
    return gameProfiles;
  }

  @JsonIgnore
  public String getStringId() {
    return id.toString();
  }
}
