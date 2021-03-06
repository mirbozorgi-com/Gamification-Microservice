package mirbozorgi.base.domain.user;

public class UpdateUserStatisticModel {

  private int incInvitations;

  private int incarsalanParticipated;

  private int incHamiCount;

  private int incGemSpent;

  private int incWin;

  private int incLose;

  public UpdateUserStatisticModel() {
  }

  public UpdateUserStatisticModel(
      int incInvitations,
      int incarsalanParticipated,
      int incHamiCount,
      int incGemSpent,
      int incWin,
      int incLose) {
    this.incInvitations = incInvitations;
    this.incarsalanParticipated = incarsalanParticipated;
    this.incHamiCount = incHamiCount;
    this.incGemSpent = incGemSpent;
    this.incWin = incWin;
    this.incLose = incLose;
  }

  public int getIncInvitations() {
    return incInvitations;
  }

  public int getIncarsalanParticipated() {
    return incarsalanParticipated;
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
