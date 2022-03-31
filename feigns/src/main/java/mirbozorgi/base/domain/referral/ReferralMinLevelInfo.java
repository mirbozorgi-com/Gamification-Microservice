package mirbozorgi.base.domain.referral;


public class ReferralMinLevelInfo {

  private int minLevelForInvitedToConsumePrize;

  public ReferralMinLevelInfo(
      int minLevelForInvitedToConsumePrize) {
    this.minLevelForInvitedToConsumePrize = minLevelForInvitedToConsumePrize;
  }

  public ReferralMinLevelInfo() {
  }

  public int getMinLevelForInvitedToConsumePrize() {
    return minLevelForInvitedToConsumePrize;
  }
}
