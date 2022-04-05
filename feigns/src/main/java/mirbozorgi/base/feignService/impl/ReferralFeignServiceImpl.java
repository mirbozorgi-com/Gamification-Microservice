package mirbozorgi.base.feignService.impl;

import mirbozorgi.base.domain.referral.AddUserReferralModel;
import mirbozorgi.base.domain.referral.ReferralMinLevelInfo;
import mirbozorgi.base.exception.GlobalExceptionService;
import mirbozorgi.base.feignService.FeignErrorFeignService;
import mirbozorgi.base.feignService.ReferralFeignService;
import mirbozorgi.base.feignService.SerializerFeignService;
import mirbozorgi.base.feigns.ReferralFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReferralFeignServiceImpl implements ReferralFeignService {

  @Autowired
  private ReferralFeign referralFeign;

  @Autowired
  private FeignErrorFeignService feignErrorFeign;

  @Autowired
  private SerializerFeignService serializerFeignService;

  @Override
  public void addUserReferral(
      String deviceId,
      String gamePackageName,
      String env,
      String uuid,
      String marketName) {
    try {

      referralFeign.addUserReferral(
          new AddUserReferralModel(deviceId),
          gamePackageName,
          env,
          uuid,
          marketName);


    } catch (RuntimeException ex) {

      GlobalExceptionService.toGlobalException(
          "addUserReferral", "referral-service", ex, feignErrorFeign
      );
    }

  }

  @Override
  public ReferralMinLevelInfo getMinLevel(
      String gamePackageName,
      String env,
      String marketName) {
    ReferralMinLevelInfo referralMinLevelInfo = null;
    try {

      Object o = referralFeign.getMinLevel(
          gamePackageName,
          env,
          marketName);
      referralMinLevelInfo = serializerFeignService
          .toObjectFromFeign(o, ReferralMinLevelInfo.class);

    } catch (RuntimeException ex) {

      GlobalExceptionService.toGlobalException(
          "getMinLevel", "referral-service", ex, feignErrorFeign
      );
    }
    return referralMinLevelInfo;
  }
}
