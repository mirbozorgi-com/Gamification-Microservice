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
@Table(name = "daily_reward",
    uniqueConstraints = @UniqueConstraint(name = "UK_DAILY_REWARD_GAME",
        columnNames = {"game_id"}))
public class DailyReward {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @ManyToOne(fetch = FetchType.LAZY, targetEntity = Game.class)
  @JoinColumn(name = "game_id", foreignKey = @ForeignKey(name = "FK_GAME_LEVEL"), nullable = false)
  private Game game;


  @Column(name = "gem")
  private int gem;

  @Column(name = "level")
  private Short level;

  @Column(name = "gold")
  private int gold;

  @Column(name = "xp")
  private int xp;

  @Column(name = "avatar_id")
  private long avatarId;

  @Column(name = "vip_period_time")
  private long vipPeriodTime;

  @Column(name = "is_add_hami")
  private Boolean addHami;

  public DailyReward() {
  }

  public DailyReward(Game game, int gem, Short level, int gold, int xp, long avatarId,
      long vipPeriodTime, Boolean addHami) {
    this.game = game;
    this.gem = gem;
    this.level = level;
    this.gold = gold;
    this.xp = xp;
    this.avatarId = avatarId;
    this.vipPeriodTime = vipPeriodTime;
    this.addHami = addHami;
  }

  public int getId() {
    return id;
  }

  public Game getGame() {
    return game;
  }

  public int getGem() {
    return gem;
  }

  public Short getLevel() {
    return level;
  }

  public int getGold() {
    return gold;
  }

  public int getXp() {
    return xp;
  }

  public long getAvatarId() {
    return avatarId;
  }

  public long getVipPeriodTime() {
    return vipPeriodTime;
  }

  public Boolean getAddHami() {
    return addHami;
  }

  public void setGem(int gem) {
    this.gem = gem;
  }

  public void setLevel(Short level) {
    this.level = level;
  }

  public void setGold(int gold) {
    this.gold = gold;
  }

  public void setXp(int xp) {
    this.xp = xp;
  }

  public void setAvatarId(long avatarId) {
    this.avatarId = avatarId;
  }

  public void setVipPeriodTime(long vipPeriodTime) {
    this.vipPeriodTime = vipPeriodTime;
  }

  public void setAddHami(Boolean addHami) {
    this.addHami = addHami;
  }
}
