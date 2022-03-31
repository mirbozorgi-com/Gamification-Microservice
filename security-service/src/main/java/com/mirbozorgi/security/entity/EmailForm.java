package com.mirbozorgi.security.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "email_form",
    uniqueConstraints =
    @UniqueConstraint(name = "UK_EMAIL_FORM_ENV_GAME_PACKAGE_NAME_NAME",
        columnNames = {"name", "game_package_name", "env"}))
public class EmailForm {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @Column(name = "name")
  private String name;

  @Column(name = "game_package_name")
  private String gamePackageName;


  @Column(name = "env")
  private String env;


  @Column(name = "file_path")
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
