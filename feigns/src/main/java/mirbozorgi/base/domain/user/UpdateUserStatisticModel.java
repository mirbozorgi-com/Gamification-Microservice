package mirbozorgi.base.domain.user;

public class UpdateUserStatisticModel {

  private int incInvitations;

  private int incGamesParticipated;

  private int incHamiCount;

  private int incGemSpent;

  private int incWin;

  private int incLose;

  public UpdateUserStatisticModel() {
  }

  public UpdateUserStatisticModel(
      int incInvitations,
      int incGamesParticipated,
      int incHamiCount,
      int incGemSpent,
      int incWin,
      int incLose) {
    this.incInvitations = incInvitations;
    this.incGamesParticipated = incGamesParticipated;
    this.incHamiCount = incHamiCount;
    this.incGemSpent = incGemSpent;
    this.incWin = incWin;
    this.incLose = incLose;
  }

  public int getIncInvitations() {
    return incInvitations;
  }

  public int getIncGamesParticipated() {
    return incGamesParticipated;
  }

  public int getIncHamiCount() {
    return incHamiCount;
  }

  public int getIncGemSpent() {
    return incGemSpent;
  }

  public int getIncWin() {
    return incWin;
  }

  public int getIncLose() {
    return incLose;
  }
}
