package mirbozorgi.base.feignService.impl;

import mirbozorgi.base.exception.GlobalExceptionService;
import mirbozorgi.base.feignService.ChallengeFeignService;
import mirbozorgi.base.feignService.FeignErrorFeignService;
import mirbozorgi.base.feignService.SerializerFeignService;
import mirbozorgi.base.feigns.ChallengeFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChallengeFeignServiceImpl implements ChallengeFeignService {

  @Autowired
  private ChallengeFeign challengeFeign;

  @Autowired
  private FeignErrorFeignService feignErrorFeign;

  @Autowired
  private SerializerFeignService serializerFeignService;

  @Override
  public Object isIncludeAllMarket(String id) {
    Object o = null;
    try {

      o = challengeFeign.isIncludeAllMarket(id);

      o = serializerFeignService.toObjectFromFeign(o, Object.class);

    } catch (RuntimeException ex) {

      GlobalExceptionService.toGlobalException(
          "isIncludeAllMarket", "challenge-service", ex, feignErrorFeign
      );


    }

    return o;
  }
}
