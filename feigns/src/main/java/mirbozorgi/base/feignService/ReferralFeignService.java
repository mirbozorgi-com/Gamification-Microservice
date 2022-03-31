package mirbozorgi.base.feignService;

import mirbozorgi.base.domain.referral.ReferralMinLevelInfo;

public interface ReferralFeignService {

  void addUserReferral(
      String deviceId,
      String gamePackageName,
      String env,
      String uuid,
      String marketName);


  ReferralMinLevelInfo getMinLevel(
      String gamePackageName,
      String env,
      String marketName);

}
