package mirbozorgi.base.feigns;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient("cohort-service")
public interface CohortFeign {

  @RequestMapping(method = RequestMethod.GET, path = "cohort/choose-random")
  Object chooseCohort(
      @RequestParam int clientVersion,
      @RequestHeader String gamePackageName,
      @RequestHeader String env,
      @RequestHeader(required = false) String marketName,
      @RequestParam(required = false) String cohort);


  @RequestMapping(method = RequestMethod.GET, path = "cohort/get-config")
  Object getConfig(
      @RequestParam String cohortName,
      @RequestHeader String gamePackageName,
      @RequestHeader String marketName);


}
