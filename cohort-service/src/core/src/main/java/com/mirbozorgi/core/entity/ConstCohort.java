package com.mirbozorgi.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "const-cohort")
public class ConstCohort {

  @Id
  private ObjectId id;

  private String gamePackageName;

  private String name;

  private Object config;

  public ConstCohort(String gamePackageName, String name, Object config) {
    this.gamePackageName = gamePackageName;
    this.name = name;
    this.config = config;
  }

  public ObjectId getId() {
    return id;
  }

  public String getGamePackageName() {
    return gamePackageName;
  }

  public String getName() {
    return name;
  }

  public Object getConfig() {
    return config;
  }

  @JsonIgnore
  public String getStringId() {
    return id.toString();
  }
}
