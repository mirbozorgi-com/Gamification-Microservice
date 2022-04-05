package com.mirbozorgi.business.service.impl;

import com.mirbozorgi.business.domain.SpinInfo;
import com.mirbozorgi.business.domain.SpinRollingInfo;
import com.mirbozorgi.business.exception.MarketException;
import com.mirbozorgi.business.exception.SpinSoonerThanConfigException;
import com.mirbozorgi.business.mapper.SpinMapper;
import com.mirbozorgi.business.service.RandomService;
import com.mirbozorgi.business.service.SerializerService;
import com.mirbozorgi.business.service.SpinService;
import com.mirbozorgi.business.service.TimeService;
import com.mirbozorgi.core.constant.ItemType;
import com.mirbozorgi.core.docuemnt.Spin;
import com.mirbozorgi.core.docuemnt.UserLastSpin;
import com.mirbozorgi.core.domain.ItemData;
import com.mirbozorgi.core.domain.SpinData;
import com.mirbozorgi.core.domain.UserLastSpinData;
import com.mirbozorgi.core.repository.document.SpinRepository;
import com.mirbozorgi.core.repository.document.UserLastSpinRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mirbozorgi.base.domain.game.GameInfo;
import mirbozorgi.base.domain.game.WalletChangeModel;
import mirbozorgi.base.domain.hami.HamiAndNotificationType;
import mirbozorgi.base.domain.user.WalletChange;
import mirbozorgi.base.feignService.GameFeignService;
import mirbozorgi.base.feignService.UserFeignService;
import mirbozorgi.base.feignService.UserHamiFeginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SpinServiceImpl implements SpinService {


  @Autowired
  private SpinRepository spinRepository;

  @Autowired
  private GameFeignService gameFeignService;

  @Autowired
  private RandomService randomService;

  @Autowired
  private UserFeignService userFeignService;

  @Value("${api.key.walletchange}")
  String apiKeyWalletChange;

  @Autowired
  private UserHamiFeginService userHamiFeginService;

  @Autowired
  private SerializerService serializerService;

  @Autowired
  private TimeService timeService;

  @Autowired
  private UserLastSpinRepository userLastSpinRepository;

  @Value("${market.default-name}")
  String defaultMarket;

  @Override
  public SpinInfo addOrUpdate(
      String gamePackageName,
      String env,
      String market,
      String key,
      long periodTime) {

    GameInfo gameInfo = gameFeignService.getByPackageNameAndEnv(gamePackageName, env);

    if (!gameInfo.getMarketNames().contains(market)) {
      throw new MarketException();
    }

    if (spinRepository.existKey(gamePackageName, env, market, key)) {
      Spin spin = spinRepository.updatePeriodTime(
          gamePackageName,
          env,
          market,
          key,
          periodTime
      );
      return SpinMapper.toInfo(spin);
    }

    Map<String, SpinData> map = new HashMap<>();
    map.put(key, new SpinData(periodTime, new ArrayList<>()));
    Spin spin = new Spin(
        gamePackageName,
        env,
        market,
        map);
    return SpinMapper.toInfo(spinRepository.save(spin));
  }

  @Override
  public void addItem(
      String key,
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
      List<Integer> avatarPurchaseIds,
      int chance) {

    GameInfo gameInfo = gameFeignService.getByPackageNameAndEnv(gamePackageName, env);

    if (!gameInfo.getMarketNames().contains(market)) {
      throw new MarketException();
    }

    ItemData itemData = new ItemData(
        config,
        types,
        vipPeriodTime,
        fileUrl,
        gold,
        gem,
        level,
        xp,
        chance,
        avatarPurchaseIds);

    if (key != null) {
      spinRepository.update(key, gamePackageName, env, market, itemData);
    }
  }

  @Override
  public List<String> getKeys(String gamePackageName, String env, String market) {

    return spinRepository.getKeys(gamePackageName, env, market);

  }

  @Override
  public SpinData getAllByKey(
      String gamePackageName,
      String env,
      String market,
      String key) {

    return spinRepository.getAllByKey(
        gamePackageName,
        env,
        market,
        key
    );
  }

  @Override
  public SpinRollingInfo rolling(
      String uuid,
      String name,
      String username,
      String gamePackageName,
      String env,
      String market,
      String key,
      int currentLevel,
      List<Integer> currentAvatarIds,
      long endVipTime
  ) {
    ItemData itemData = null;
    WalletChange walletChange = new WalletChange();
    GameInfo gameInfo = gameFeignService.getByPackageNameAndEnv(gamePackageName, env);
    long nowTime = timeService.getNowUnixFromInstantClass();
    if (!gameInfo.getMarketNames().contains(market)) {
      throw new MarketException();
    }

    if (market == null) {
      market = defaultMarket;
    }

    Spin spin = spinRepository.findSpin(gamePackageName, env, market);

    UserLastSpin userLastSpin = userLastSpinRepository.findByUuId(uuid);

    if (userLastSpin != null) {
      if (userLastSpin.getLastTimeOfSpin()
          .get(fix(gamePackageName))
          .get(env)
          .get(market) != null) {
        UserLastSpinData userLastSpinData = userLastSpin.getLastTimeOfSpin()
            .get(fix(gamePackageName))
            .get(env)
            .get(market)
            .get(key);
        SpinData spinData = spin.getSpinDatas().get(key);

        if (nowTime - userLastSpinData.getTime() < spinData.getTimePeriod()) {
          throw new SpinSoonerThanConfigException();
        }
      }
    }

    if (key != null) {

      List<ItemData> itemDatas = spin.getSpinDatas().get(key).getItemDatas();

      int[] chances = itemDatas.stream().mapToInt(i -> i.getChance()).toArray();

      int index = randomService.nextInt(chances) - 1;

      itemData = itemDatas.get(index);

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
                  false,
                  endVipTime),
              gamePackageName,
              uuid,
              env,
              market
          );

          walletChange.setGem(itemData.getGem());
          walletChange.setGold(itemData.getGold());
          walletChange.setLevel(itemData.getLevel());
          walletChange.setXp(itemData.getXp());


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
              nowTime,
              itemData.getVipPeriodTime() + nowTime,
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
              name,
              serializerService.toJson(itemData.getConfig()),
              HamiAndNotificationType.BUY,
              gamePackageName,
              env,
              market);
          walletChange.setHamiAded(true);

        }
      }

      userLastSpinRepository.add(
          uuid, key,
          gamePackageName, env, market, nowTime
      );

    }

    assert itemData != null;
    return new SpinRollingInfo(
        itemData.getConfig(),
        walletChange);
  }

  @Override
  public UserLastSpinData getAllUserSpin(
      String uuid,
      String gamePackageName,
      String env,
      String market,
      String key) {

    try {
      UserLastSpin userLastSpin = userLastSpinRepository.findByUuId(uuid);
      return userLastSpin
          .getLastTimeOfSpin()
          .get(fix(gamePackageName))
          .get(env)
          .get(market)
          .get(key);
    } catch (Exception e) {
      return null;
    }

  }

  @Override
  public SpinInfo updateItems(
      String key,
      String gamePackageName,
      String env,
      String market,
      List<ItemData> itemDatas) {

    return SpinMapper.toInfo(spinRepository.updateItemSpin(
        key,
        gamePackageName,
        env,
        market,
        itemDatas
    ));


  }

  private String fix(String packageName) {
    return packageName.replace(".", "_");
  }
}
