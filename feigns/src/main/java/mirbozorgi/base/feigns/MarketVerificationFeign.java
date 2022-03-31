package mirbozorgi.base.feigns;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient("marketverification-service")
public interface MarketVerificationFeign {

  @RequestMapping(value = "api/v1/market/{market}/verify/{packageName}/sku/{sku}/token/{token}", method = RequestMethod.GET)
  Object verifyPath(
      @PathVariable("market") String market,
      @PathVariable("packageName") String packageName,
      @PathVariable("sku") String sku,
      @PathVariable("token") String token
  );

  @RequestMapping(value = "api/v1/trace-hilla", method = RequestMethod.GET)
  Object traceHilla(
      @RequestParam String packageName
      , @RequestParam String marketName
      , @RequestParam(required = false, defaultValue = "prod") String env
      , @RequestParam String orderId
      , @RequestParam String transactionId
      , @RequestParam String skuName);
}
