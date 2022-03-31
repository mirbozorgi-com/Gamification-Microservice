package com.mirbozorgi.business.domain.user;

import java.util.List;

public class UserGetWalletAndStatistic {

  private int gem;

  private int gold;

  private Short level;

  private Integer xp;

  private List<Integer> activeAvatarIds;

  private List<Integer> purchasedAvatars;

  private int win;

  private int lose;

  private int invitations;

  private int gamesParticipated;

  private int hamiCount;

  private int gemSpent;


  public UserGetWalletAndStatistic() {
  }

  public UserGetWalletAndStatistic(
      int gem,
      int gold,
      Short level,
      Integer xp,
      List<Integer> activeAvatarIds,
      List<Integer> purchasedAvatars,
      int win,
      int lose,
      int invitations,
      int gamesParticipated,
      int hamiCount,
      int gemSpent) {
    this.gem = gem;
    this.gold = gold;
    this.level = level;
    this.xp = xp;
    this.activeAvatarIds = activeAvatarIds;
    this.purchasedAvatars = purchasedAvatars;
    this.win = win;
    this.lose = lose;
    this.invitations = invitations;
    this.gamesParticipated = gamesParticipated;
    this.hamiCount = hamiCount;
    this.gemSpent = gemSpent;
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

  public int getWin() {
    return win;
  }

  public int getLose() {
    return lose;
  }

  public int getInvitations() {
    return invitations;
  }

  public int getGamesParticipated() {
    return gamesParticipated;
  }

  public int getHamiCount() {
    return hamiCount;
  }

  public int getGemSpent() {
    return gemSpent;
  }
}
