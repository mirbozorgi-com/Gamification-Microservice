package mirbozorgi.base.domain.game;

public class DailyRewardInfo {

  private int id;
  private int gameId;
  private int gem;
  private Short level;
  private int gold;
  private int xp;
  private long avatarId;
  private long vipPeriodTime;
  private Boolean addHami;

  public DailyRewardInfo(int id, int gameId, int gem, Short level, int gold, int xp, long avatarId,
      long vipPeriodTime, Boolean addHami) {
    this.id = id;
    this.gameId = gameId;
    this.gem = gem;
    this.level = level;
    this.gold = gold;
    this.xp = xp;
    this.avatarId = avatarId;
    this.vipPeriodTime = vipPeriodTime;
    this.addHami = addHami;
  }

  public DailyRewardInfo() {
  }

  public int getId() {
    return id;
  }

  public int getGameId() {
    return gameId;
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
}
