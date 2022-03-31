package mirbozorgi.base.domain.security;

public class GetDeviceIdInfo {

  private String deviceId;

  public GetDeviceIdInfo() {
  }

  public GetDeviceIdInfo(String deviceId) {
    this.deviceId = deviceId;
  }

  public String getDeviceId() {
    return deviceId;
  }
}
