package mirbozorgi.base.domain.user;

import java.util.List;

public class Wallet {


  private int gem;

  private int gold;

  private Short level;

  private Integer xp;

  private List<Integer> activeAvatarIds;

  private List<Integer> purchasedAvatars;


  public Wallet(int gem, int gold, Short level, Integer xp, List<Integer> activeAvatarIds,
      List<Integer> purchasedAvatars) {
    this.gem = gem;
    this.gold = gold;
    this.level = level;
    this.xp = xp;
    this.activeAvatarIds = activeAvatarIds;
    this.purchasedAvatars = purchasedAvatars;
  }

  public Wallet() {
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

  public List<Integer> getActiveAvatarIds() {
    return activeAvatarIds;
  }

  public List<Integer> getPurchasedAvatars() {
    return purchasedAvatars;
  }
}
