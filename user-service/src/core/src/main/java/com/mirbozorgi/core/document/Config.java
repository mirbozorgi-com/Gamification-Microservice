package com.mirbozorgi.core.document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashMap;
import java.util.Map;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "config")
public class Config {

  @Id
  private ObjectId id;

  private String name;


  private Map<String, Object> configs;

  public Config(String name, Map<String, Object> configs) {
    this.name = name;
    this.configs = configs;
  }

  public Config() {
    configs = new HashMap<>();
  }

  public String getName() {
    return name;
  }

  public Map<String, Object> getConfigs() {
    return configs;
  }

  public ObjectId getId() {
    return id;
  }

  @JsonIgnore
  public String getStringId() {
    return id.toString();
  }
}