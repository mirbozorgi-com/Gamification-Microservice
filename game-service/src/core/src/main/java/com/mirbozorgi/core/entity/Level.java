package com.mirbozorgi.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "level",
    uniqueConstraints = @UniqueConstraint(name = "UK_LEVEL_MIN_XP_MAX_XP",
        columnNames = {"min_xp", "max_xp", "game_id"}))
public class Level {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "level_name")
  private String levelName;

  @Column(name = "level_number")
  private int levelNumber;

  @ManyToOne(fetch = FetchType.LAZY, targetEntity = Game.class)
  @JoinColumn(name = "game_id", foreignKey = @ForeignKey(name = "FK_GAME_LEVEL"), nullable = false)
  private Game game;

  @Column(name = "min_xp")
  private int minXp;

  @Column(name = "max_xp")
  private int maxXp;

  public Level(String levelName, int levelNumber, Game game, int minXp, int maxXp) {
    this.levelName = levelName;
    this.levelNumber = levelNumber;
    this.game = game;
    this.minXp = minXp;
    this.maxXp = maxXp;
  }

  public Level() {
  }

  public int getLevelNumber() {
    return levelNumber;
  }

  public int getId() {
    return id;
  }

  public String getLevelName() {
    return levelName;
  }

  public Game getGame() {
    return game;
  }

  public int getMinXp() {
    return minXp;
  }

  public int getMaxXp() {
    return maxXp;
  }

  public void setLevelName(String levelName) {
    this.levelName = levelName;
  }

  public void setLevelNumber(int levelNumber) {
    this.levelNumber = levelNumber;
  }

  public void setGame(Game game) {
    this.game = game;
  }

  public void setMinXp(int minXp) {
    this.minXp = minXp;
  }

  public void setMaxXp(int maxXp) {
    this.maxXp = maxXp;
  }
}
