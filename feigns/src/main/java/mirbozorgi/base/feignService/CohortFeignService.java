package mirbozorgi.base.feignService;

import mirbozorgi.base.domain.cohort.ChooseCohortData;
import mirbozorgi.base.domain.cohort.GetConfigCohortData;

public interface CohortFeignService {

  ChooseCohortData chooseCohort(
      int clientVersion,
      String packageName,
      String env,
      String market,
      String cohort);


  GetConfigCohortData getConfig(
      String cohortName,
      String packageName,
      String marketName);
}
