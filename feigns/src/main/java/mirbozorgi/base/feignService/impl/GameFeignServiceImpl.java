package mirbozorgi.base.feignService.impl;

import java.util.ArrayList;
import java.util.HashMap;
import mirbozorgi.base.domain.game.AvatarInfo;
import mirbozorgi.base.domain.game.DailyRewardInfo;
import mirbozorgi.base.domain.game.GameDetailInfo;
import mirbozorgi.base.domain.game.GameInfo;
import mirbozorgi.base.domain.game.LevelInfo;
import mirbozorgi.base.domain.market.MarketInfo;
import mirbozorgi.base.domain.sku.SkuInfo;
import mirbozorgi.base.exception.GlobalExceptionService;
import mirbozorgi.base.feignService.FeignErrorFeignService;
import mirbozorgi.base.feignService.GameFeignService;
import mirbozorgi.base.feignService.SerializerFeignService;
import mirbozorgi.base.feigns.GameFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameFeignServiceImpl implements GameFeignService {

  @Autowired
  private SerializerFeignService serializerFeignService;
  @Autowired
  private GameFeign gameFeign;


  @Autowired
  private FeignErrorFeignService feignErrorFeign;


  @Override
  public GameInfo getByPackageNameAndEnv(String packageName, String env) {
    GameInfo gameInfo = null;
    try {

      Object o = gameFeign.getByPackageNameAndEnv(packageName, env);

      gameInfo = serializerFeignService.toObjectFromFeign(o, GameInfo.class);

    } catch (RuntimeException ex) {

      GlobalExceptionService
          .toGlobalException("getByPackageNameAndEnv", "game-service", ex, feignErrorFeign);


    }

    return gameInfo;
  }

  @Override
  public MarketInfo getMarketByName(String name) {
    MarketInfo marketInfo = null;
    try {

      Object o = gameFeign.getMarketByName(name);

      marketInfo = serializerFeignService.toObjectFromFeign(o, MarketInfo.class);

    } catch (RuntimeException ex) {

      GlobalExceptionService.toGlobalException(
          "getMarketByName", "game-service", ex, feignErrorFeign
      );


    }

    return marketInfo;
  }

  @Override
  public GameInfo getGameById(int id) {
    GameInfo gameInfo = null;
    try {

      Object o = gameFeign.getGameById(id);

      gameInfo = serializerFeignService.toObjectFromFeign(o, GameInfo.class);

    } catch (RuntimeException ex) {

      GlobalExceptionService.toGlobalException(
          "getGameById", "game-service", ex, feignErrorFeign
      );


    }
    return gameInfo;

  }

  @Override
  public SkuInfo getSkuBy(int gameId, String name) {
    SkuInfo skuInfo = null;
    try {

      Object o = gameFeign.getSkuBy(gameId, name);

      skuInfo = serializerFeignService.toObjectFromFeign(o, SkuInfo.class);

    } catch (RuntimeException ex) {

      GlobalExceptionService.toGlobalException(
          "getSkuBy", "game-service", ex, feignErrorFeign
      );

    }
    return skuInfo;
  }

  @Override
  public SkuInfo getSkuById(int id) {
    SkuInfo skuInfo = null;
    try {

      Object o = gameFeign.getSkuById(id);

      skuInfo = serializerFeignService.toObjectFromFeign(o, SkuInfo.class);

    } catch (RuntimeException ex) {

      GlobalExceptionService.toGlobalException(
          "getSkuById", "game-service", ex, feignErrorFeign
      );
    }
    return skuInfo;
  }

  @Override
  public MarketInfo getMarketByNameFullResponse(String name) {
    MarketInfo marketInfo = null;
    try {

      Object o = gameFeign.getMarketByNameFullResponse(name);

      marketInfo = serializerFeignService.toObjectFromFeign(o, MarketInfo.class);

    } catch (RuntimeException ex) {

      GlobalExceptionService.toGlobalException(
          "getMarketByNameFullResponse", "game-service", ex, feignErrorFeign
      );


    }
    return marketInfo;
  }

  @Override
  public GameDetailInfo getGameDetail(long id) {
    GameDetailInfo gameDetailInfo = null;
    try {

      Object o = gameFeign.getGameDetail(id);

      gameDetailInfo = serializerFeignService.toObjectFromFeign(o, GameDetailInfo.class);

    } catch (RuntimeException ex) {

      GlobalExceptionService.toGlobalException(
          "getGameDetail", "game-service", ex, feignErrorFeign
      );

    }

    return gameDetailInfo;
  }

  @Override
  public GameDetailInfo getGameDetailByPublicKey(String publicKey) {
    GameDetailInfo gameDetailInfo = null;
    try {

      Object o = gameFeign.getGameDetailByPublicKey(publicKey);

      gameDetailInfo = serializerFeignService.toObjectFromFeign(o, GameDetailInfo.class);

    } catch (RuntimeException ex) {

      GlobalExceptionService.toGlobalException(
          "getGameDetailByPublicKey", "game-service", ex, feignErrorFeign
      );


    }
    return gameDetailInfo;
  }

  @Override
  public AvatarInfo getAvatarById(int id) {
    AvatarInfo avatarInfo = null;
    try {

      Object o = gameFeign.getAvatarById(id);

      avatarInfo = serializerFeignService.toObjectFromFeign(o, AvatarInfo.class);

    } catch (RuntimeException ex) {

      GlobalExceptionService.toGlobalException(
          "getAvatarById", "game-service", ex, feignErrorFeign
      );


    }
    return avatarInfo;
  }

  @Override
  public LevelInfo getCurrentLevel(
      String gamePackageName,
      String env,
      int currentXp) {
    LevelInfo levelInfo = null;
    try {

      Object o = gameFeign.getCurrentLevel(
          gamePackageName,
          env,
          currentXp
      );

      levelInfo = serializerFeignService.toObjectFromFeign(o, LevelInfo.class);

    } catch (RuntimeException ex) {

      GlobalExceptionService.toGlobalException(
          "getCurrentLevel", "game-service", ex, feignErrorFeign
      );


    }
    return levelInfo;
  }

  @Override
  public DailyRewardInfo getDailyRewardBy(String gamePackageName, String env) {
    DailyRewardInfo dailyRewardInfo = null;
    try {

      Object o = gameFeign.getDailyRewardBy(
          gamePackageName,
          env
      );

      dailyRewardInfo = serializerFeignService.toObjectFromFeign(o, DailyRewardInfo.class);

    } catch (RuntimeException ex) {

      GlobalExceptionService.toGlobalException(
          "getDailyRewardBy", "game-service", ex, feignErrorFeign
      );


    }
    return dailyRewardInfo;
  }

  @Override
  public ArrayList getAllDailyContinues(
      String name,
      String gamePackageName,
      String env) {

    ArrayList walletChangeModels = new ArrayList<>();

    try {

      Object o = gameFeign.getAllDailyContinues(
          name,
          gamePackageName,
          env
      );

      walletChangeModels = serializerFeignService.toObjectFromFeign(o, ArrayList.class);

    } catch (RuntimeException ex) {

      GlobalExceptionService.toGlobalException(
          "getAllDailyContinues", "game-service", ex, feignErrorFeign
      );


    }
    return walletChangeModels;
  }

  @Override
  public HashMap getAllNetworks(String gamePackageName, String env) {
    HashMap allNetworks = new HashMap();

    try {

      Object o = gameFeign.getAllNetworks(
          gamePackageName,
          env
      );

      allNetworks = serializerFeignService.toObjectFromFeign(o, HashMap.class);

    } catch (RuntimeException ex) {

      GlobalExceptionService.toGlobalException(
          "getAllNetworks", "game-service", ex, feignErrorFeign
      );


    }
    return allNetworks;
  }


  @Override
  public String getDefaultMarket(String gamePackageName, String env) {
    Object o = null;
    try {

      o = gameFeign.getDefaultMarket(
          gamePackageName, env);

      o = serializerFeignService.toObjectFromFeign(o, Object.class);


    } catch (RuntimeException ex) {

      GlobalExceptionService.toGlobalException(
          "getDefaultMarket", "game-service", ex, feignErrorFeign
      );

    }
    assert o != null;
    return o.toString();
  }

}

