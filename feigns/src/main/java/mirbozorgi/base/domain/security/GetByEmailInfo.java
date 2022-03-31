package mirbozorgi.base.domain.security;

public class GetByEmailInfo {

  private String deviceId;
  private String uuid;

  public GetByEmailInfo() {
  }

  public GetByEmailInfo(String deviceId, String uuid) {
    this.deviceId = deviceId;
    this.uuid = uuid;
  }

  public String getDeviceId() {
    return deviceId;
  }

  public String getUuid() {
    return uuid;
  }
}
