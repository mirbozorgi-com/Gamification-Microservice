package mirbozorgi.base.domain.leaderboard;

import java.util.List;

public class UpdateXpModel {

  private int xp;
  private int xpGlobal;
  private int level;
  private String username;
  private List<Integer> avatarIds;
  private long endVipTime;

  public UpdateXpModel(
      int xp,
      int level,
      String username,
      List<Integer> avatarIds,
      long endVipTime,
      int xpGlobal) {
    this.xp = xp;
    this.xpGlobal = xpGlobal;
    this.level = level;
    this.username = username;
    this.avatarIds = avatarIds;
    this.endVipTime = endVipTime;
  }

  public int getXpGlobal() {
    return xpGlobal;
  }

  public long getEndVipTime() {
    return endVipTime;
  }

  public int getLevel() {
    return level;
  }

  public List<Integer> getAvatarIds() {
    return avatarIds;
  }

  public int getXp() {
    return xp;
  }

  public String getUsername() {
    return username;
  }
}
