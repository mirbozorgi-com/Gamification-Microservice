package mirbozorgi.base.feigns;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient("challenge-service")
public interface ChallengeFeign {

  @RequestMapping(method = RequestMethod.GET, path = "challenge/check-include-all-market")
  Object isIncludeAllMarket(@RequestParam String id);


}
