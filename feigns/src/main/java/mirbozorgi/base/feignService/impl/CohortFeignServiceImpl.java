package mirbozorgi.base.feignService.impl;

import mirbozorgi.base.domain.cohort.ChooseCohortData;
import mirbozorgi.base.domain.cohort.GetConfigCohortData;
import mirbozorgi.base.exception.GlobalExceptionService;
import mirbozorgi.base.feignService.CohortFeignService;
import mirbozorgi.base.feignService.FeignErrorFeignService;
import mirbozorgi.base.feignService.SerializerFeignService;
import mirbozorgi.base.feigns.CohortFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CohortFeignServiceImpl implements CohortFeignService {

  @Autowired
  private CohortFeign cohortFeign;

  @Autowired
  private FeignErrorFeignService feignErrorFeign;

  @Autowired
  private SerializerFeignService serializerFeignService;


  @Override
  public ChooseCohortData chooseCohort(
      int clientVersion,
      String packageName,
      String env,
      String market,
      String cohort) {
    ChooseCohortData chooseCohortData = null;
    try {

      Object o = cohortFeign.chooseCohort(
          clientVersion,
          packageName,
          env,
          market,
          cohort
      );

      chooseCohortData = serializerFeignService.toObjectFromFeign(o, ChooseCohortData.class);

    } catch (RuntimeException ex) {

      GlobalExceptionService.toGlobalException(
          "chooseCohort", "cohort-service", ex, feignErrorFeign
      );


    }

    return chooseCohortData;
  }

  @Override
  public GetConfigCohortData getConfig(
      String cohortName,
      String packageName,
      String marketName) {
    GetConfigCohortData getConfigCohortData = null;
    try {

      Object o = cohortFeign.getConfig(
          cohortName,
          packageName,
          marketName
      );

      getConfigCohortData = serializerFeignService.toObjectFromFeign(o, GetConfigCohortData.class);

    } catch (RuntimeException ex) {

      GlobalExceptionService.toGlobalException(
          "getConfig", "cohort-service", ex, feignErrorFeign
      );


    }

    return getConfigCohortData;
  }
}
