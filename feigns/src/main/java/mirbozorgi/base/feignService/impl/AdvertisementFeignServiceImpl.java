package mirbozorgi.base.feignService.impl;

import mirbozorgi.base.domain.advertisement.PackageThirdPartyInfo;
import mirbozorgi.base.exception.GlobalExceptionService;
import mirbozorgi.base.feignService.AdvertisementFeignService;
import mirbozorgi.base.feignService.FeignErrorFeignService;
import mirbozorgi.base.feignService.SerializerFeignService;
import mirbozorgi.base.feigns.AdvertisementFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdvertisementFeignServiceImpl implements AdvertisementFeignService {

  @Autowired
  private FeignErrorFeignService feignErrorFeign;

  @Autowired
  private SerializerFeignService serializerFeignService;

  @Autowired
  private AdvertisementFeign advertisementFeign;

  @Override
  public PackageThirdPartyInfo getBy(
      String gamePackageName,
      String env,
      String marketName,
      String name) {
    PackageThirdPartyInfo packageThirdPartyInfo = null;
    try {

      Object o = advertisementFeign.getBy(
          gamePackageName,
          env,
          marketName,
          name
      );

      packageThirdPartyInfo = serializerFeignService
          .toObjectFromFeign(o, PackageThirdPartyInfo.class);

    } catch (RuntimeException ex) {

      GlobalExceptionService.toGlobalException(
          "getBy", "advertisement-service", ex, feignErrorFeign
      );


    }
    return packageThirdPartyInfo;
  }
}
