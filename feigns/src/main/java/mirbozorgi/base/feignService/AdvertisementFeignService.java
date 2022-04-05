package mirbozorgi.base.feignService;

import mirbozorgi.base.domain.advertisement.PackageThirdPartyInfo;

public interface AdvertisementFeignService {

  PackageThirdPartyInfo getBy(
      String gamePackageName,
      String env,
      String marketName,
      String name);
}
