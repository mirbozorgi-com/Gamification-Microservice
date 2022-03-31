package mirbozorgi.base.feignService;

import mirbozorgi.base.domain.security.GetDeviceIdInfo;

public interface SecurityFeignService {

  GetDeviceIdInfo getDeviceId(String uuid,
      String gamePackageName,
      String marketName,
      String env);

}
