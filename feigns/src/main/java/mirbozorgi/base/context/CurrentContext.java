package mirbozorgi.base.context;

public class CurrentContext {

  private long userId;
  private String uuid;
  private String role;
  private String gamePackageName;
  private String env;
  private String market;

  public String getGamePackageName() {
    return gamePackageName;
  }

  public String getEnv() {
    return env;
  }

  public String getMarket() {
    return market;
  }

  public long getUserId() {
    return userId;
  }

  public String getUuid() {
    return uuid;
  }

  public String getRole() {
    return role;
  }

  public CurrentContext() {
  }

  public CurrentContext(
      String gamePackageName,
      String env,
      String market,
      String uuid,
      String role) {
    this.gamePackageName = gamePackageName;
    this.env = env;
    this.market = market;
    this.uuid = uuid;
    this.role = role;
  }

  public CurrentContext(
      String gamePackageName,
      String env,
      String market,
      String uuid) {
    this.gamePackageName = gamePackageName;
    this.env = env;
    this.market = market;
    this.uuid = uuid;
  }


  public CurrentContext(
      String gamePackageName,
      String env,
      String market) {
    this.gamePackageName = gamePackageName;
    this.env = env;
    this.market = market;
  }


  public CurrentContext(String uuid) {
    this.uuid = uuid;
  }

  public CurrentContext(long userId, String uuid, String role) {
    this.userId = userId;
    this.uuid = uuid;
    this.role = role;
  }


}
