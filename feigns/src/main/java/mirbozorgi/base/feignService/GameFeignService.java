package mirbozorgi.base.feignService;

import java.util.ArrayList;
import java.util.HashMap;
import mirbozorgi.base.domain.game.AvatarInfo;
import mirbozorgi.base.domain.game.DailyRewardInfo;
import mirbozorgi.base.domain.game.GameDetailInfo;
import mirbozorgi.base.domain.game.GameInfo;
import mirbozorgi.base.domain.game.LevelInfo;
import mirbozorgi.base.domain.market.MarketInfo;
import mirbozorgi.base.domain.sku.SkuInfo;

public interface GameFeignService {

  GameInfo getByPackageNameAndEnv(String packageName, String env);

  MarketInfo getMarketByName(String name);

  GameInfo getGameById(int id);

  SkuInfo getSkuBy(int gameId, String name);

  SkuInfo getSkuById(int id);

  MarketInfo getMarketByNameFullResponse(String name);

  GameDetailInfo getGameDetail(long id);

  GameDetailInfo getGameDetailByPublicKey(String publicKey);

  AvatarInfo getAvatarById(int id);

  LevelInfo getCurrentLevel(
      String gamePackageName,
      String env,
      int currentXp);


  DailyRewardInfo getDailyRewardBy(
      String gamePackageName,
      String env);

  ArrayList getAllDailyContinues(
      String name,
      String gamePackageName,
      String env);

  HashMap getAllNetworks(String gamePackageName,
      String env);


  String getDefaultMarket(
      String gamePackageName,
      String env);
}
