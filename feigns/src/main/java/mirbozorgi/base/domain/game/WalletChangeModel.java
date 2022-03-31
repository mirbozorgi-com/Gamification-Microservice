package mirbozorgi.base.domain.game;


import java.util.List;

public class WalletChangeModel {

  private int gem;
  private int gold;
  private Short level;
  private int xp;
  private List<Integer> currentAvatarIds;
  private int currentLevel;
  private Boolean gemBuy;
  private long endVipTime;

  public WalletChangeModel(int gem,
      int gold,
      Short level,
      int xp,
      List<Integer> currentAvatarIds,
      int currentLevel,
      Boolean gemBuy,
      long endVipTime) {
    this.gem = gem;
    this.gold = gold;
    this.level = level;
    this.xp = xp;
    this.currentAvatarIds = currentAvatarIds;
    this.currentLevel = currentLevel;
    this.gemBuy = gemBuy;
    this.endVipTime = endVipTime;
  }

  public long getEndVipTime() {
    return endVipTime;
  }

  public Boolean getGemBuy() {
    return gemBuy;
  }

  public int getCurrentLevel() {
    return currentLevel;
  }

  public List<Integer> getCurrentAvatarIds() {
    return currentAvatarIds;
  }

  public int getGem() {
    return gem;
  }

  public int getGold() {
    return gold;
  }

  public Short getLevel() {
    return level;
  }

  public Integer getXp() {
    return xp;
  }
}
