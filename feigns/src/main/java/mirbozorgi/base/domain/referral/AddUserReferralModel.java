package mirbozorgi.base.domain.referral;


public class AddUserReferralModel {

  private String deviceId;

  public AddUserReferralModel(String deviceId) {
    this.deviceId = deviceId;
  }

  public AddUserReferralModel() {
  }

  public String getDeviceId() {
    return deviceId;
  }
}
