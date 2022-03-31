package com.mirbozorgi.business.domain.user;

public class UserStatistic {


  private int win;

  private int lose;

  private int invitations;

  private int gamesParticipated;

  private int hamiCount;

  private int gemSpent;

  public UserStatistic() {
  }

  public UserStatistic(
      int win,
      int lose,
      int invitations,
      int gamesParticipated,
      int hamiCount,
      int gemSpent) {
    this.win = win;
    this.lose = lose;
    this.invitations = invitations;
    this.gamesParticipated = gamesParticipated;
    this.hamiCount = hamiCount;
    this.gemSpent = gemSpent;
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
