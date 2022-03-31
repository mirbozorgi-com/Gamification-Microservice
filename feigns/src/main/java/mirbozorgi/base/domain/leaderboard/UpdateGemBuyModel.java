package mirbozorgi.base.domain.leaderboard;

import java.util.List;

public class UpdateGemBuyModel {

  private int gem;
  private int level;
  private String username;
  private List<Integer> avatarIds;
  private long endVipTime;


  public UpdateGemBuyModel(
      int gem,
      int level,
      String username,
      List<Integer> avatarIds,
      long endVipTime) {
    this.gem = gem;
    this.level = level;
    this.username = username;
    this.avatarIds = avatarIds;
    this.endVipTime = endVipTime;
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

  public int getGem() {
    return gem;
  }

  public String getUsername() {
    return username;
  }
}
