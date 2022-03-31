package mirbozorgi.base.domain.user;

import java.util.ArrayList;
import java.util.List;

public class WalletChange {

  private int gem;

  private int gold;

  private Short level;

  private int xp;

  private boolean hamiAded;

  private List<Integer> avatarIds;

  private long addedVipPeriodTime;


  public WalletChange() {
    gem = 0;
    gold = 0;
    xp = 0;
    level = (short) 0;
    hamiAded = false;
    avatarIds = new ArrayList<>();
    addedVipPeriodTime = 0;

  }

  public WalletChange(
      int gem,
      int gold,
      Short level,
      int xp,
      boolean hamiAded,
      List<Integer> avatarIds,
      long addedVipPeriodTime) {
    this.gem = gem;
    this.gold = gold;
    this.level = level;
    this.xp = xp;
    this.hamiAded = hamiAded;
    this.avatarIds = avatarIds;
    this.addedVipPeriodTime = addedVipPeriodTime;
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

  public int getXp() {
    return xp;
  }

  public boolean isHamiAded() {
    return hamiAded;
  }

  public List<Integer> getAvatarIds() {
    return avatarIds;
  }

  public long getAddedVipPeriodTime() {
    return addedVipPeriodTime;
  }

  public void setGem(int gem) {
    this.gem = gem;
  }

  public void setGold(int gold) {
    this.gold = gold;
  }

  public void setLevel(Short level) {
    this.level = level;
  }

  public void setXp(int xp) {
    this.xp = xp;
  }

  public void setHamiAded(boolean hamiAded) {
    this.hamiAded = hamiAded;
  }

  public void setAvatarIds(List<Integer> avatarIds) {
    this.avatarIds = avatarIds;
  }

  public void setAddedVipPeriodTime(long addedVipPeriodTime) {
    this.addedVipPeriodTime = addedVipPeriodTime;
  }
}
