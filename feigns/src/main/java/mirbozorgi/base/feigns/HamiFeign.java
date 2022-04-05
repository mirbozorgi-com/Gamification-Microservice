package mirbozorgi.base.feigns;

import mirbozorgi.base.domain.hami.AddModel;
import mirbozorgi.base.domain.hami.UserVipAddModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Service
@FeignClient("hami-service")
public interface HamiFeign {

  @RequestMapping(method = RequestMethod.POST, path = "hami/add")
  Object addHami(
      @RequestBody AddModel model,
      @RequestHeader String gamePackageName,
      @RequestHeader String uuid,
      @RequestHeader String env,
      @RequestHeader String marketName);


  @RequestMapping(method = RequestMethod.POST, path = "vip/add")
  Object addVip(
      @Validated @RequestBody UserVipAddModel model,
      @RequestHeader String gamePackageName,
      @RequestHeader String uuid,
      @RequestHeader String env,
      @RequestHeader String marketName);


  @RequestMapping(method = RequestMethod.GET, path = "vip/check")
  Object checkVip(
      @RequestHeader String gamePackageName,
      @RequestHeader String uuid,
      @RequestHeader String env,
      @RequestHeader String marketName);


  @RequestMapping(method = RequestMethod.GET, path = "vip/check-end-time")
  Object checkVipTime(
      @RequestHeader String gamePackageName,
      @RequestHeader String uuid,
      @RequestHeader String env,
      @RequestHeader String marketName);


}
