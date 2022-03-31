package com.mirbozorgi.core.entity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "config", uniqueConstraints = @UniqueConstraint(columnNames = {"key", "cohort"}))
public class Config {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  @Access(AccessType.PROPERTY)
  private Long id;

  @Column(name = "key")
  private String key;

  @Column(name = "value", length = 100)
  private String value;

  @Column(name = "cohort", length = 10)
  private String cohort;

  public Config() {
  }

  public Config(String key, String value, String cohort) {
    this.key = key;
    this.value = value;
    this.cohort = cohort;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getCohort() {
    return cohort;
  }

  public void setCohort(String cohort) {
    this.cohort = cohort;
  }
}