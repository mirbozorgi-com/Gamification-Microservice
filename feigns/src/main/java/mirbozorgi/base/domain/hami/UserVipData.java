package mirbozorgi.base.domain.hami;

public class UserVipData {

  private String uuid;

  private String gamePackageName;

  private String env;

  private String marketName;

  private long startDate;

  private long endDate;

  private long createdDate;

  public String getUuid() {
    return uuid;
  }

  public String getGamePackageName() {
    return gamePackageName;
  }

  public String getEnv() {
    return env;
  }

  public String getMarketName() {
    return marketName;
  }

  public long getStartDate() {
    return startDate;
  }

  public long getEndDate() {
    return endDate;
  }

  public long getCreatedDate() {
    return createdDate;
  }


  public UserVipData() {
  }

  public UserVipData(String uuid, String gamePackageName, String env, String marketName,
      long startDate, long endDate, long createdDate) {
    this.uuid = uuid;
    this.gamePackageName = gamePackageName;
    this.env = env;
    this.marketName = marketName;
    this.startDate = startDate;
    this.endDate = endDate;
    this.createdDate = createdDate;
  }
}
