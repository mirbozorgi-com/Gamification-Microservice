package com.mirbozorgi.core.domain;

import java.util.HashMap;
import java.util.Map;

public class Statistic {


  private int win;

  private int lose;

  private int invitations;

  private int gamesParticipated;

  private int hamiCount;

  private int gemSpent;

  private int firstPlaceOfWeeklyXp;

  private int yearPassedFromRegister;

  private Map<String, Boolean> isFollowNetworks;

  public Statistic() {
    isFollowNetworks = new HashMap<>();
  }

  public Statistic(
      int win,
      int lose,
      int invitations,
      int gamesParticipated,
      int hamiCount,
      int gemSpent,
      int firstPlaceOfWeeklyXp,
      int yearPassedFromRegister,
      Map<String, Boolean> isFollowNetworks) {
    this.win = win;
    this.lose = lose;
    this.invitations = invitations;
    this.gamesParticipated = gamesParticipated;
    this.hamiCount = hamiCount;
    this.gemSpent = gemSpent;
    this.firstPlaceOfWeeklyXp = firstPlaceOfWeeklyXp;
    this.yearPassedFromRegister = yearPassedFromRegister;
    this.isFollowNetworks = isFollowNetworks;
  }

  public Map<String, Boolean> getIsFollowNetworks() {
    return isFollowNetworks;
  }

  public int getYearPassedFromRegister() {
    return yearPassedFromRegister;
  }

  public int getFirstPlaceOfWeeklyXp() {
    return firstPlaceOfWeeklyXp;
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
