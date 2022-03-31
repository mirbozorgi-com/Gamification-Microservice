package mirbozorgi.base.domain.advertisement;

import java.util.ArrayList;
import java.util.List;
import mirbozorgi.base.domain.user.WalletChange;

public class PackageThirdPartyInfo {

  private int id;
  private String name;
  private Object config;
  private long secondsPassedBetweenWatching;
  private WalletChange walletChange;
  private String gamePackageName;
  private String env;
  private String marketName;
  private String activityType;

  private List<ThirdPartyAdvertisementData> thirdPartyAdvertisements;


  public PackageThirdPartyInfo(int id, String name, Object config,
      long secondsPassedBetweenWatching,
      WalletChange walletChange, String gamePackageName, String env, String marketName,
      String activityType,
      List<ThirdPartyAdvertisementData> thirdPartyAdvertisements) {
    this.id = id;
    this.name = name;
    this.thirdPartyAdvertisements = thirdPartyAdvertisements;
    this.config = config;
    this.secondsPassedBetweenWatching = secondsPassedBetweenWatching;
    this.walletChange = walletChange;
    this.gamePackageName = gamePackageName;
    this.env = env;
    this.marketName = marketName;
    this.activityType = activityType;
  }

  public PackageThirdPartyInfo() {
    thirdPartyAdvertisements = new ArrayList<>();
  }

  public List<ThirdPartyAdvertisementData> getThirdPartyAdvertisements() {
    return thirdPartyAdvertisements;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }


  public Object getConfig() {
    return config;
  }

  public long getSecondsPassedBetweenWatching() {
    return secondsPassedBetweenWatching;
  }

  public WalletChange getWalletChange() {
    return walletChange;
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

  public String getActivityType() {
    return activityType;
  }
}
