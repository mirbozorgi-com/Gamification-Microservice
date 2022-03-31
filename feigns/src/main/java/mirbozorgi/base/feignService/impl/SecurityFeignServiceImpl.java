package mirbozorgi.base.feignService.impl;

import mirbozorgi.base.domain.security.GetDeviceIdInfo;
import mirbozorgi.base.exception.GlobalExceptionService;
import mirbozorgi.base.feignService.FeignErrorFeignService;
import mirbozorgi.base.feignService.SecurityFeignService;
import mirbozorgi.base.feignService.SerializerFeignService;
import mirbozorgi.base.feigns.SecurityFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecurityFeignServiceImpl implements SecurityFeignService {

  @Autowired
  private SecurityFeign securityFeign;

  @Autowired
  private FeignErrorFeignService feignErrorFeign;

  @Autowired
  private SerializerFeignService serializerFeignService;

  @Override
  public GetDeviceIdInfo getDeviceId(String uuid,
      String gamePackageName,
      String marketName,
      String env) {
    GetDeviceIdInfo getDeviceIdInfo = null;
    try {

      Object o = securityFeign.getDeviceId(uuid,
          marketName,
          gamePackageName,
          env);

      getDeviceIdInfo = serializerFeignService.toObjectFromFeign(o, GetDeviceIdInfo.class);

    } catch (RuntimeException ex) {

      GlobalExceptionService.toGlobalException(
          "getDeviceId", "security-service", ex, feignErrorFeign
      );

    }

    return getDeviceIdInfo;
  }
}
