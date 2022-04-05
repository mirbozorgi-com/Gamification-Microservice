package com.mirbozorgi.business.service.impl;

import com.mirbozorgi.business.exception.GiftCodeUsedException;
import com.mirbozorgi.business.service.GiftCodeService;
import com.mirbozorgi.business.service.TimeService;
import com.mirbozorgi.business.service.UsedGiftCodeService;
import com.mirbozorgi.business.domain.GiftCodeInfo;
import com.mirbozorgi.business.domain.GiftCodeUseData;
import com.mirbozorgi.business.mapper.GiftCodeUsedMapper;
import com.mirbozorgi.core.docuemnt.UsedGiftCode;
import com.mirbozorgi.core.repository.document.UsedGiftCodeRepository;
import java.util.ArrayList;
import java.util.List;
import mirbozorgi.base.domain.game.WalletChangeModel;
import mirbozorgi.base.domain.hami.HamiAndNotificationType;
import mirbozorgi.base.domain.user.UserGetGlobalResponse;
import mirbozorgi.base.domain.user.WalletChange;
import mirbozorgi.base.feignService.UserFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UsedGiftCodeServiceImpl implements UsedGiftCodeService {

  @Autowired
  private UsedGiftCodeRepository usedGiftCodeRepository;

  @Autowired
  private TimeService timeService;

  @Autowired
  private GiftCodeService giftCodeService;

  @Autowired
  private UserFeignService userFeignService;

  @Autowired
  private UserHamiFeginService userHamiFeginService;

  @Value("${api.key.walletchange}")
  String apiKeyWalletChange;

  @Override
  public GiftCodeUseData add(
      String userUuId,
      String code,
      String gamePackageName,
      String env,
      String marketName,
      int currentLevel,
      List<Integer> currentAvatarIds,
      long endVipTime) {

    GiftCodeInfo giftCodeInfo = giftCodeService.findBy(code, gamePackageName, marketName, env);
    WalletChange walletChange = giftCodeInfo.getWalletChange();
    UsedGiftCode giftCode = usedGiftCodeRepository.getBy(
        code,
        gamePackageName,
        env,
        marketName,
        userUuId);
    if (giftCode != null) {
      throw new GiftCodeUsedException();
    }

    UserGetGlobalResponse user = userFeignService.getByUuId(
        userUuId,
        gamePackageName,
        env,
        marketName
    );

    giftCodeService.useGiftCode(
        user.getClientVersion(),
        null,
        null,
        code,
        env,
        gamePackageName,
        marketName
    );

    UsedGiftCode usedGiftCode = usedGiftCodeRepository.addUsedGiftCode(new UsedGiftCode(
        code,
        timeService.getNowUnixFromInstantClass(),
        gamePackageName,
        env,
        marketName,
        userUuId
    ));

    /////wallet changing ////////////////
    if (walletChange.getGem() != 0 || walletChange.getXp() != 0 || walletChange.getGold() != 0) {
      userFeignService.walletChange(
          apiKeyWalletChange,
          new WalletChangeModel(
              walletChange.getGem(),
              walletChange.getGold(),
              walletChange.getLevel(),
              walletChange.getXp(),
              currentAvatarIds,
              currentLevel,
              false,
              endVipTime),
          gamePackageName,
          userUuId,
          env,
          marketName
      );
    }
    if (walletChange.getAvatarIds().size() != 0) {

      userFeignService.addAvatarPurchase(
          userUuId,
          gamePackageName,
          env,
          marketName,
          walletChange.getAvatarIds()
      );


    }
    if (walletChange.getAddedVipPeriodTime() != 0) {
      userHamiFeginService.addVipUser(
          userUuId,
          timeService.getNowUnixFromInstantClass(),
          walletChange.getAddedVipPeriodTime() + timeService.getNowUnixFromInstantClass(),
          walletChange.getAddedVipPeriodTime(),
          gamePackageName,
          env,
          marketName
      );
    }

    if (walletChange.isHamiAded()) {
      userHamiFeginService.addHami(
          userUuId,
          user.getUsername(),
          "BY_GIFT_CODE",
          null,
          HamiAndNotificationType.GIFT_CODE,
          gamePackageName,
          env,
          marketName);
    }
    /////wallet changing ////////////////

    return GiftCodeUsedMapper.toGiftCodeUsedData(usedGiftCode, walletChange);
  }

  @Override
  public GiftCodeUseData getBy(
      String userUuId,
      String code,
      String gamePackageName,
      String env,
      String marketName) {

    return GiftCodeUsedMapper.toGiftCodeUsedData(
        usedGiftCodeRepository.getBy(
            code,
            gamePackageName,
            env,
            marketName,
            userUuId
        ),
        null
    );
  }

  @Override
  public List<GiftCodeUseData> getAll(
      String code,
      String gamePackageName,
      String env,
      String marketName,
      String userUuId) {
    List<GiftCodeUseData> giftCodeUseData = new ArrayList<>();

    List<UsedGiftCode> usedGiftCodes = usedGiftCodeRepository.getAll(
        code,
        gamePackageName,
        env,
        marketName,
        userUuId
    );
    for (UsedGiftCode usedGiftCode : usedGiftCodes) {
      giftCodeUseData.add(GiftCodeUsedMapper.toGiftCodeUsedData(usedGiftCode, null));
    }

    return giftCodeUseData;
  }

  private String fix(String packageName) {
    return packageName.replace(".", "_");
  }
}
