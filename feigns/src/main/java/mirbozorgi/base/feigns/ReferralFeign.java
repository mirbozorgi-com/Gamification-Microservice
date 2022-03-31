package mirbozorgi.base.feigns;

import mirbozorgi.base.domain.referral.AddUserReferralModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Service
@FeignClient("referral-service")
public interface ReferralFeign {

  @RequestMapping(value = "user-referral/add-my-referral-data", method = RequestMethod.POST)
  Object addUserReferral(@RequestBody AddUserReferralModel model,
      @RequestHeader String gamePackageName,
      @RequestHeader String env,
      @RequestHeader String uuid,
      @RequestHeader String marketName);

  @RequestMapping(value = "referral-prize/get-min-level", method = RequestMethod.GET)
  Object getMinLevel(
      @RequestHeader String gamePackageName,
      @RequestHeader String env,
      @RequestHeader String marketName);
}
