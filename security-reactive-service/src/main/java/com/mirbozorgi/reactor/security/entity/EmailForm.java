package com.mirbozorgi.reactor.security.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


@Table("email_form")
public class EmailForm {

  @Id
  private int id;


  private String name;


  private String gamePackageName;


  private String env;


  private String filePath;

  public EmailForm(String name, String gamePackageName, String env, String filePath) {
    this.name = name;
    this.gamePackageName = gamePackageName;
    this.env = env;
    this.filePath = filePath;
  }

  public EmailForm() {
  }

  public int getId() {
    return id;
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

  public String getFilePath() {
    return filePath;
  }
}
