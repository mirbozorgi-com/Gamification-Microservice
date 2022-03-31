package mirbozorgi.base.feignService;

import mirbozorgi.base.domain.hami.CheckVipTimeInfo;
import mirbozorgi.base.domain.hami.HamiAndNotificationType;
import mirbozorgi.base.domain.hami.UserHamiData;
import mirbozorgi.base.domain.hami.UserVipData;

public interface UserHamiFeginService {

  UserHamiData addHami(
      String uuid,
      String username,
      String name,
      String config,
      HamiAndNotificationType hamiType,
      String gamePackageName,
      String env,
      String marketName);


  UserVipData addVipUser(
      String uuid,
      long startDate,
      long endDate,
      long periodExtraTime,
      String gamePackageName,
      String env,
      String marketName);

  boolean checkVip(
      String uuid,
      String gamePackageName,
      String env,
      String marketName
  );

  CheckVipTimeInfo checkVipTime(
      String uuid,
      String gamePackageName,
      String env,
      String marketName
  );

}
