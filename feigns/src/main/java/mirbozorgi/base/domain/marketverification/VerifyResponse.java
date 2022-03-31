package mirbozorgi.base.domain.marketverification;


public class VerifyResponse {

  private Boolean success;

  private String token;

  private int consumptionState;

  private int purchaseState;

  private String developerPayload;

  public VerifyResponse() {
  }

  public VerifyResponse(
      Boolean success,
      String token,
      int consumptionState,
      int purchaseState,
      String developerPayload) {
    this.success = success;
    this.token = token;
    this.consumptionState = consumptionState;
    this.purchaseState = purchaseState;
    this.developerPayload = developerPayload;
  }

  public Boolean getSuccess() {
    return success;
  }

  public String getToken() {
    return token;
  }

  public int getConsumptionState() {
    return consumptionState;
  }

  public int getPurchaseState() {
    return purchaseState;
  }

  public String getDeveloperPayload() {
    return developerPayload;
  }
}
