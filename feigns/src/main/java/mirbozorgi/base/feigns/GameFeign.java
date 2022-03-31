package mirbozorgi.base.feigns;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient(name = "game-service")
public interface GameFeign {

  @RequestMapping(method = RequestMethod.GET, path = "game/get-by")
  Object getByPackageNameAndEnv(@RequestParam String gamePackageName, @RequestParam String env);


  @RequestMapping(method = RequestMethod.GET, path = "market/get-by-name")
  Object getMarketByName(@RequestParam String name);


  @RequestMapping(method = RequestMethod.GET, path = "game/get")
  Object getGameById(@RequestParam int id);


  @RequestMapping(method = RequestMethod.GET, path = "sku/get-by")
  Object getSkuBy(@RequestParam int gameId, @RequestParam String name);

  @RequestMapping(method = RequestMethod.GET, path = "sku/get")
  Object getSkuById(@RequestParam int id);


  @RequestMapping(method = RequestMethod.GET, path = "market/get-by-name-full-response")
  Object getMarketByNameFullResponse(@RequestParam String name);

  @RequestMapping(method = RequestMethod.GET, path = "game-detail/get")
  Object getGameDetail(@RequestParam long id);


  @RequestMapping(method = RequestMethod.GET, path = "game-detail/get-by")
  Object getGameDetailByPublicKey(@RequestParam String publicKey);


  @RequestMapping(method = RequestMethod.GET, path = "avatar/get")
  Object getAvatarById(@RequestParam int id);


  @RequestMapping(value = "level/get-current-by-xp", method = RequestMethod.GET)
  Object getCurrentLevel(
      @RequestParam String gamePackageName,
      @RequestParam String env,
      @RequestParam int currentXp);


  @RequestMapping(value = "daily-reward/get-by", method = RequestMethod.GET)
  Object getDailyRewardBy(
      @RequestParam String gamePackageName,
      @RequestParam String env);


  @RequestMapping(value = "daily-reward/continues/get-all-daily", method = RequestMethod.GET)
  Object getAllDailyContinues(
      @RequestParam(required = false, defaultValue = "DEF") String name,
      @RequestHeader String gamePackageName,
      @RequestHeader String env);

  @RequestMapping(value = "social-network-follow-reward/get-all", method = RequestMethod.GET)
  Object getAllNetworks(@RequestHeader String gamePackageName,
      @RequestHeader String env);


  @RequestMapping(value = "default-market/memory/get", method = RequestMethod.GET)
  Object getDefaultMarket(
      @RequestParam String gamePackageName,
      @RequestParam String env);

}