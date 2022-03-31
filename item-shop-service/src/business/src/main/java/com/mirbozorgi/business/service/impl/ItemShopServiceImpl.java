package com.mirbozorgi.business.service.impl;

import com.mirbozorgi.business.exception.ConsumptionException;
import com.mirbozorgi.business.exception.NotFoundException;
import com.mirbozorgi.business.exception.PurchaseCredentialException;
import com.mirbozorgi.business.mapper.ItemShopMapper;
import com.mirbozorgi.business.service.ItemShopService;
import com.mirbozorgi.business.service.TimeService;
import com.mirbozorgi.business.domain.ItemShopData;
import com.mirbozorgi.business.domain.ItemShopFindAll;
import com.mirbozorgi.business.exception.ConsumptionBeforeException;
import com.mirbozorgi.business.exception.ConsumptionClientException;
import com.mirbozorgi.core.constant.ItemType;
import com.mirbozorgi.core.docuemnt.ItemShop;
import com.mirbozorgi.core.domain.ItemData;
import com.mirbozorgi.core.repository.document.ItemShopRepository;
import com.mirbozorgi.core.repository.memory.AddPurchaseRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mirbozorgi.base.domain.game.GameInfo;
import mirbozorgi.base.domain.game.WalletChangeModel;
import mirbozorgi.base.domain.hami.HamiAndNotificationType;
import mirbozorgi.base.domain.marketverification.VerifyResponse;
import mirbozorgi.base.domain.user.WalletChange;
import mirbozorgi.base.exception.InvalidMarketException;
import mirbozorgi.base.feignService.GameFeignService;
import mirbozorgi.base.feignService.MarketVerificationService;
import mirbozorgi.base.feignService.UserFeignService;
import mirbozorgi.base.feignService.UserHamiFeginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ItemShopServiceImpl implements ItemShopService {


  @Autowired
  private ItemShopRepository itemShopRepository;

  @Autowired
  private GameFeignService gameFeignService;

  @Autowired
  private UserFeignService userFeignService;

  @Autowired
  private UserHamiFeginService userHamiFeginService;

  @Value("${api.key.walletchange}")
  String apiKeyWalletChange;

  @Autowired
  private TimeService timeService;

  @Autowired
  private MarketVerificationService marketVerificationService;

  @Autowired
  private AddPurchaseRepository addPurchaseRepository;


  @Value("${market.default-name}")
  String defaultMarket;

  @Override
  public ItemShopData save(
      String name,
      String gamePackageName,
      String env,
      String market,
      Object config,
      List<ItemType> types,
      long vipPeriodTime,
      String fileUrl,
      int xp,
      int gold,
      int gem,
      Short level,
      List<Integer> avatarPurchaseIds) {
    GameInfo gameInfo = gameFeignService.getByPackageNameAndEnv(gamePackageName, env);
    if (!gameInfo.getMarketNames().contains(market)) {
      throw new InvalidMarketException();
    }
    ItemData itemDataNew = new ItemData(
        config,
        types,
        vipPeriodTime,
        fileUrl,
        gold,
        gem,
        level,
        xp,
        0,
        avatarPurchaseIds);
    Map<String, ItemData> itemDataMap = new HashMap<>();
    itemDataMap.put(name, itemDataNew);

    ItemShop itemShop = new ItemShop(
        itemDataMap,
        gamePackageName,
        env,
        market);

    Boolean entity = itemShopRepository.findEntity(gamePackageName, env, market);
    if (!entity) {
      return ItemShopMapper
          .toItemShopData(itemShopRepository.save(itemShop),
              name);
    }

    ItemData itemData = itemShopRepository.findOne(
        name,
        gamePackageName,
        env,
        market);
    if (itemData == null) {
      ItemShop update = itemShopRepository.update(name, gamePackageName, env, market, itemDataNew);
      return ItemShopMapper
          .toItemShopData(update,
              name);
    }

    return null;

  }


  @Override
  public ItemShopData update(
      String name,
      String gamePackageName,
      String env,
      String market,
      Object config,
      List<ItemType> type,
      long vipPeriodTime,
      int gold,
      int gem,
      int level,
      int xp,
      int chance,
      List<Integer> avatarPurchaseIds,
      String fileUrl) {

    ItemShop itemShop = itemShopRepository.update(
        name,
        gamePackageName,
        env,
        market,
        new ItemData(
            config,
            type,
            vipPeriodTime,
            fileUrl,
            gold,
            gem,
            (short) level,
            xp,
            chance,
            avatarPurchaseIds)
    );

    return ItemShopMapper.toItemShopData(itemShop, name);
  }

  @Override
  public void delete(String name,
      String gamePackageName,
      String env,
      String market) {

    itemShopRepository.delete(
        name,
        gamePackageName,
        env,
        market
    );
  }

  @Override
  public List<ItemShopFindAll> findAll(
      String gamePackageName,
      String env,
      String market) {

    List<ItemShopFindAll> itemShopDataList = new ArrayList<>();
    Map<String, ItemData> all = itemShopRepository.findAll(
        gamePackageName,
        env,
        market
    );

    for (String name : all.keySet()) {
      Boolean isHami = false;

      if (all.get(name).getType().contains(ItemType.HAMI)) {
        isHami = true;
      }

      WalletChange walletChange = new WalletChange(
          all.get(name).getGem(),
          all.get(name).getGold(),
          all.get(name).getLevel(),
          all.get(name).getXp(),
          isHami,
          all.get(name).getAvatarPurchaseIds(),
          all.get(name).getVipPeriodTime());
      itemShopDataList.add(new ItemShopFindAll(
          name,
          all.get(name).getConfig(),
          all.get(name).getType(),
          all.get(name).getFileUrl(),
          walletChange
      ));

    }

    return itemShopDataList;
  }

  @Override
  public ItemData findOne(
      String name,
      String gamePackageName,
      String env,
      String market) {
    return itemShopRepository.findOne(name, gamePackageName, env, market);
  }

  @Override
  public WalletChange buy(
      String uuid,
      String username,
      String skuName,
      String gamePackageName,
      String env,
      String market,
      String marketToken,
      int currentLevel,
      List<Integer> currentAvatarIds,
      long endVipTime
  ) {
    if (market == null) {
      market = defaultMarket;
    }
    VerifyResponse verify = marketVerificationService
        .verify(
            market,
            gamePackageName,
            skuName,
            marketToken);
    if (!verify.getSuccess()) {
      throw new PurchaseCredentialException();
    }

    if (verify.getConsumptionState() == 0) {
      throw new ConsumptionBeforeException();
    }

    if (addPurchaseRepository.get(uuid, marketToken) != null) {
      throw new ConsumptionException();
    }

    ItemData itemData = itemShopRepository.findOne(skuName, gamePackageName, env, market);
    if (itemData == null) {
      throw new NotFoundException();
    }
    WalletChange walletChange = new WalletChange();
    for (ItemType itemType : itemData.getType()) {

      if (itemType.equals(ItemType.NORMAL)) {
        userFeignService.walletChange(
            apiKeyWalletChange,
            new WalletChangeModel(
                itemData.getGem(),
                itemData.getGold(),
                itemData.getLevel(),
                itemData.getXp(),
                currentAvatarIds,
                currentLevel,
                true,
                endVipTime),
            gamePackageName,
            uuid,
            env,
            market
        );
        walletChange.setXp(itemData.getXp());
        walletChange.setLevel(itemData.getLevel());
        walletChange.setGold(itemData.getGold());
        walletChange.setGem(itemData.getGem());

      } else if (itemType.equals(ItemType.AVATAR_ID)) {

        userFeignService.addAvatarPurchase(
            uuid,
            gamePackageName,
            env,
            market,
            itemData.getAvatarPurchaseIds()
        );

        walletChange.setAvatarIds(itemData.getAvatarPurchaseIds());

      } else if (itemType.equals(ItemType.VIP)) {
        userHamiFeginService.addVipUser(
            uuid,
            timeService.getNowUnixFromInstantClass(),
            itemData.getVipPeriodTime() + timeService.getNowUnixFromInstantClass(),
            itemData.getVipPeriodTime(),
            gamePackageName,
            env,
            market
        );
        walletChange.setAddedVipPeriodTime(itemData.getVipPeriodTime());
      } else if (itemType.equals(ItemType.HAMI)) {
        userHamiFeginService.addHami(
            uuid,
            username,
            "WITH SKU :" + skuName,
            null,
            HamiAndNotificationType.BUY,
            gamePackageName,
            env,
            market);
        walletChange.setHamiAded(true);

      }

    }
    //In CAFE BAZAAR state 1 means that user did not consume him/her purchase
    addPurchaseRepository.add(uuid, marketToken, "1", 7200);
    return walletChange;
  }

  @Override
  public WalletChange buyMyket(
      String uuid,
      String username,
      String skuName,
      String gamePackageName,
      String env,
      String market,
      String marketToken,
      int currentLevel,
      List<Integer> currentAvatarIds,
      long endVipTime) {
    if (market == null) {
      market = defaultMarket;
    }
    VerifyResponse verify = marketVerificationService
        .verify(
            market,
            gamePackageName,
            skuName,
            marketToken);
    if (!verify.getSuccess()) {
      throw new PurchaseCredentialException();
    }

    if (verify.getConsumptionState() == 1) {
      throw new ConsumptionBeforeException();
    }

    if (addPurchaseRepository.get(uuid, marketToken) != null) {
      throw new ConsumptionException();
    }

    ItemData itemData = itemShopRepository.findOne(skuName, gamePackageName, env, market);
    if (itemData == null) {
      throw new NotFoundException();
    }
    WalletChange walletChange = new WalletChange();
    for (ItemType itemType : itemData.getType()) {

      if (itemType.equals(ItemType.NORMAL)) {
        userFeignService.walletChange(
            apiKeyWalletChange,
            new WalletChangeModel(
                itemData.getGem(),
                itemData.getGold(),
                itemData.getLevel(),
                itemData.getXp(),
                currentAvatarIds,
                currentLevel,
                true,
                endVipTime),
            gamePackageName,
            uuid,
            env,
            market
        );
        walletChange.setXp(itemData.getXp());
        walletChange.setLevel(itemData.getLevel());
        walletChange.setGold(itemData.getGold());
        walletChange.setGem(itemData.getGem());

      } else if (itemType.equals(ItemType.AVATAR_ID)) {

        userFeignService.addAvatarPurchase(
            uuid,
            gamePackageName,
            env,
            market,
            itemData.getAvatarPurchaseIds()
        );

        walletChange.setAvatarIds(itemData.getAvatarPurchaseIds());

      } else if (itemType.equals(ItemType.VIP)) {
        userHamiFeginService.addVipUser(
            uuid,
            timeService.getNowUnixFromInstantClass(),
            itemData.getVipPeriodTime() + timeService.getNowUnixFromInstantClass(),
            itemData.getVipPeriodTime(),
            gamePackageName,
            env,
            market
        );
        walletChange.setAddedVipPeriodTime(itemData.getVipPeriodTime());
      } else if (itemType.equals(ItemType.HAMI)) {
        userHamiFeginService.addHami(
            uuid,
            username,
            "WITH SKU :" + skuName,
            null,
            HamiAndNotificationType.BUY,
            gamePackageName,
            env,
            market);
        walletChange.setHamiAded(true);

      }

    }
    //In myket state 0 means that user did not consume him/her purchase
    addPurchaseRepository.add(uuid, marketToken, "0", 7200);
    return walletChange;
  }

  @Override
  public WalletChange hillaBuy(
      String uuid,
      String username,
      String skuName,
      String gamePackageName,
      String env,
      String market,
      int currentLevel,
      List<Integer> currentAvatarIds,
      long endVipTime,
      String orderId,
      String transactionId) {
    if (market == null) {
      market = defaultMarket;
    }
    VerifyResponse verify = marketVerificationService
        .hillaVerify(
            market,
            gamePackageName,
            skuName,
            orderId,
            transactionId,
            env);
    if (!verify.getSuccess()) {
      throw new PurchaseCredentialException();
    }

    //We don't have any consumption in hillaPay so we can't check consumption with market
//    if (verify.getConsumptionState() == 0) {
//      throw new ConsumptionBeforeException();
//    }

    if (addPurchaseRepository.get(uuid, orderId) != null) {
      throw new ConsumptionException();
    }

    ItemData itemData = itemShopRepository.findOne(skuName, gamePackageName, env, market);
    if (itemData == null) {
      throw new NotFoundException();
    }
    WalletChange walletChange = new WalletChange();
    for (ItemType itemType : itemData.getType()) {

      if (itemType.equals(ItemType.NORMAL)) {
        userFeignService.walletChange(
            apiKeyWalletChange,
            new WalletChangeModel(
                itemData.getGem(),
                itemData.getGold(),
                itemData.getLevel(),
                itemData.getXp(),
                currentAvatarIds,
                currentLevel,
                true,
                endVipTime),
            gamePackageName,
            uuid,
            env,
            market
        );
        walletChange.setXp(itemData.getXp());
        walletChange.setLevel(itemData.getLevel());
        walletChange.setGold(itemData.getGold());
        walletChange.setGem(itemData.getGem());

      } else if (itemType.equals(ItemType.AVATAR_ID)) {

        userFeignService.addAvatarPurchase(
            uuid,
            gamePackageName,
            env,
            market,
            itemData.getAvatarPurchaseIds()
        );

        walletChange.setAvatarIds(itemData.getAvatarPurchaseIds());

      } else if (itemType.equals(ItemType.VIP)) {
        userHamiFeginService.addVipUser(
            uuid,
            timeService.getNowUnixFromInstantClass(),
            itemData.getVipPeriodTime() + timeService.getNowUnixFromInstantClass(),
            itemData.getVipPeriodTime(),
            gamePackageName,
            env,
            market
        );
        walletChange.setAddedVipPeriodTime(itemData.getVipPeriodTime());
      } else if (itemType.equals(ItemType.HAMI)) {
        userHamiFeginService.addHami(
            uuid,
            username,
            "WITH SKU :" + skuName,
            null,
            HamiAndNotificationType.BUY,
            gamePackageName,
            env,
            market);
        walletChange.setHamiAded(true);

      }

    }
    addPurchaseRepository.add(uuid, orderId, "1", 7200);
    return walletChange;
  }

  @Override
  public void consumePurchaseMyket(
      String uuid,
      String skuName,
      String gamePackageName,
      String env,
      String market,
      String marketToken
  ) {
    VerifyResponse verify = marketVerificationService
        .verify(
            market,
            gamePackageName,
            skuName,
            marketToken);
    if (!verify.getSuccess()) {
      throw new PurchaseCredentialException();
    }
    if (addPurchaseRepository.get(uuid, marketToken) == null) {
      throw new ConsumptionBeforeException();
    }
    if (verify.getConsumptionState() == 0) {
      throw new ConsumptionClientException();
    }
    addPurchaseRepository.remove(uuid, marketToken);
  }

  @Override
  public void consumePurchase(
      String uuid,
      String skuName,
      String gamePackageName,
      String env,
      String market,
      String marketToken) {
    VerifyResponse verify = marketVerificationService
        .verify(
            market,
            gamePackageName,
            skuName,
            marketToken);
    if (!verify.getSuccess()) {
      throw new PurchaseCredentialException();
    }
    if (addPurchaseRepository.get(uuid, marketToken) == null) {
      throw new ConsumptionBeforeException();
    }
    if (verify.getConsumptionState() == 1) {
      throw new ConsumptionClientException();
    }
    addPurchaseRepository.remove(uuid, marketToken);
  }

  @Override
  public void consumeHillaPurchase(
      String uuid,
      String skuName,
      String gamePackageName,
      String env,
      String market,
      String orderId,
      String transactionId
  ) {
    VerifyResponse verify = marketVerificationService
        .hillaVerify(
            market,
            gamePackageName,
            skuName,
            orderId,
            transactionId,
            env);
    if (!verify.getSuccess()) {
      throw new PurchaseCredentialException();
    }
    if (addPurchaseRepository.get(uuid, orderId) == null) {
      throw new ConsumptionBeforeException();
    }
    if (verify.getConsumptionState() == 1) {
      throw new ConsumptionClientException();
    }
    addPurchaseRepository.remove(uuid, orderId);

  }
}
