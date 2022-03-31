package mirbozorgi.base.domain.advertisement;


public class ThirdPartyAdvertisementData {

  private int id;
  private String name;
  private int priority;
  private String providerId;
  private int packageThirdPartyId;

  public ThirdPartyAdvertisementData() {
  }

  public ThirdPartyAdvertisementData(
      int id,
      String name,
      int priority,
      String providerId,
      int packageThirdPartyId) {
    this.name = name;
    this.priority = priority;
    this.providerId = providerId;
    this.packageThirdPartyId = packageThirdPartyId;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public int getPriority() {
    return priority;
  }

  public String getProviderId() {
    return providerId;
  }

  public int getPackageThirdPartyId() {
    return packageThirdPartyId;
  }
}
