package mirbozorgi.base.domain.leaderboard;

import java.util.List;

public class UpdateReferalModel {


  private int quantity;
  private int level;
  private String username;
  private List<Integer> avatarIds;
  private long endVipTime;

  public UpdateReferalModel(
      int quantity,
      int level,
      String username,
      List<Integer> avatarIds,
      long endVipTime) {
    this.quantity = quantity;
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

  public int getQuantity() {
    return quantity;
  }

  public String getUsername() {
    return username;
  }
}