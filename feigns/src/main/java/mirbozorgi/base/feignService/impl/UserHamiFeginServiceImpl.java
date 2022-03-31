package mirbozorgi.base.feignService.impl;

import mirbozorgi.base.domain.hami.AddModel;
import mirbozorgi.base.domain.hami.CheckVipTimeInfo;
import mirbozorgi.base.domain.hami.HamiAndNotificationType;
import mirbozorgi.base.domain.hami.UserHamiData;
import mirbozorgi.base.domain.hami.UserVipAddModel;
import mirbozorgi.base.domain.hami.UserVipData;
import mirbozorgi.base.exception.GlobalExceptionService;
import mirbozorgi.base.feignService.FeignErrorFeignService;
import mirbozorgi.base.feignService.SerializerFeignService;
import mirbozorgi.base.feignService.UserHamiFeginService;
import mirbozorgi.base.feigns.HamiFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserHamiFeginServiceImpl implements UserHamiFeginService {


  @Autowired
  private HamiFeign hamiFeign;

  @Autowired
  private FeignErrorFeignService feignErrorFeign;

  @Autowired
  private SerializerFeignService serializerFeignService;

  @Override
  public UserHamiData addHami(
      String uuid,
      String username,
      String name,
      String config,
      HamiAndNotificationType hamiType,
      String gamePackageName,
      String env,
      String marketName) {
    UserHamiData userHamiData = null;
    try {

      Object o = hamiFeign.addHami(
          new AddModel(
              username,
              name,
              config,
              hamiType),
          gamePackageName,
          uuid,
          env,
          marketName
      );
      userHamiData = serializerFeignService.toObjectFromFeign(o, UserHamiData.class);


    } catch (RuntimeException ex) {

      GlobalExceptionService.toGlobalException(
          "addHami", "hami-service", ex, feignErrorFeign
      );

    }

    return userHamiData;
  }

  @Override
  public UserVipData addVipUser(
      String uuid,
      long startDate,
      long endDate,
      long periodExtraTime,
      String gamePackageName,
      String env,
      String marketName) {

    UserVipData userVipData = null;
    try {

      Object o = hamiFeign.addVip(
          new UserVipAddModel(
              startDate,
              endDate,
              periodExtraTime
          ),
          gamePackageName,
          uuid,
          env,
          marketName
      );
      userVipData = serializerFeignService.toObjectFromFeign(o, UserVipData.class);

    } catch (RuntimeException ex) {

      GlobalExceptionService.toGlobalException(
          "addVipUser", "hami-service", ex, feignErrorFeign
      );

    }

    return userVipData;
  }


  @Override
  public boolean checkVip(
      String uuid,
      String gamePackageName,
      String env,
      String marketName) {
    Object o = null;
    try {

      o = hamiFeign.checkVip(
          gamePackageName,
          uuid,
          env,
          marketName
      );
      o = serializerFeignService.toObjectFromFeign(o, Object.class);


    } catch (RuntimeException ex) {

      GlobalExceptionService.toGlobalException(
          "checkVip", "hami-service", ex, feignErrorFeign
      );

    }
    assert o != null;
    return o.equals(true);
  }

  @Override
  public CheckVipTimeInfo checkVipTime(
      String uuid,
      String gamePackageName,
      String env,
      String marketName) {
    CheckVipTimeInfo checkVipTimeInfo = null;
    try {

      Object o = hamiFeign.checkVipTime(
          gamePackageName,
          uuid,
          env,
          marketName
      );
      checkVipTimeInfo = serializerFeignService.toObjectFromFeign(o, CheckVipTimeInfo.class);

    } catch (RuntimeException ex) {

      GlobalExceptionService.toGlobalException(
          "checkVipTime", "hami-service", ex, feignErrorFeign
      );

    }

    return checkVipTimeInfo;
  }


}
