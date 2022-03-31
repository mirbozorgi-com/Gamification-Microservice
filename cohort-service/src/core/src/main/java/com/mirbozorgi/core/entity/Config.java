package com.mirbozorgi.core.entity;

import com.mirbozorgi.core.entity.config.ServerConfig;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "config")
public class Config {

  @Id
  private String cohort;

  private ServerConfig server;

  public Config() {
    this.server = new ServerConfig();
  }

  public String getCohort() {
    return cohort;
  }

  public ServerConfig getServer() {
    return server;
  }
}