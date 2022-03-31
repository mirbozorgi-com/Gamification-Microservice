package com.mirbozorgi.business.service.impl;

import com.mirbozorgi.business.exception.GiftCodeExpireOrNotActiveException;
import com.mirbozorgi.business.exception.NotFoundException;
import com.mirbozorgi.business.service.GiftCodeService;
import com.mirbozorgi.business.service.TimeService;
import com.mirbozorgi.business.domain.GiftCodeInfo;
import com.mirbozorgi.business.exception.UseGiftCodeException;
import com.mirbozorgi.business.mapper.GiftCodeMapper;
import com.mirbozorgi.core.docuemnt.GiftCode;
import com.mirbozorgi.core.repository.document.GiftCodeRepository;
import java.util.ArrayList;
import java.util.List;
import mirbozorgi.base.domain.user.WalletChange;
import mirbozorgi.base.feignService.GameFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GiftCodeServiceImpl implements GiftCodeService {

  @Autowired
  private GiftCodeRepository giftCodeRepository;

  @Autowired
  private TimeService timeService;

  @Autowired
  private GameFeignService gameFeignService;


  @Override
  public GiftCodeInfo save(
      String name,
      String code,
      String gamePackageName,
      String env,
      String marketName,
      WalletChange walletChange,
      long expireDate,
      int maxUseCountGlobal,
      boolean isActive,
      int minClientVersion,
      int maxClientVersion,
      int minLevelPermission,
      int daysAfterInstall) {
    gameFeignService.getByPackageNameAndEnv(gamePackageName, env);

    gameFeignService.getMarketByName(marketName);

    if (maxClientVersion == 0) {
      maxClientVersion = Integer.MAX_VALUE;
    }

    return GiftCodeMapper.toGiftCodeInfo(giftCodeRepository.save(new GiftCode(
        name,
        code,
        gamePackageName,
        marketName,
        env,
        walletChange,
        timeService.getNowUnixFromInstantClass(),
        expireDate,
        maxUseCountGlobal,
        isActive,
        minClientVersion,
        maxClientVersion,
        minLevelPermission,
        daysAfterInstall
    )));
  }


  @Override
  public void delete(String id) {
    GiftCode giftCode = giftCodeRepository.findById(id);
    if (giftCode == null) {
      throw new NotFoundException();
    }
    giftCodeRepository.delete(giftCode);
  }

  @Override
  public GiftCodeInfo findById(String id) {
    GiftCode giftCode = giftCodeRepository.findById(id);
    if (giftCode == null) {
      throw new NotFoundException();
    }
    return GiftCodeMapper.toGiftCodeInfo(giftCode);
  }

  @Override
  public List<GiftCodeInfo> findAll(
      String name,
      String code,
      String gamePackageName,
      String env,
      String marketName) {
    List<GiftCodeInfo> giftCodeInfos = new ArrayList<>();
    List<GiftCode> giftCodes = giftCodeRepository
        .findAll(name, code, gamePackageName, env, marketName);
    for (GiftCode giftCode : giftCodes) {
      giftCodeInfos.add(GiftCodeMapper.toGiftCodeInfo(giftCode));
    }

    return giftCodeInfos;
  }


  @Override
  public GiftCodeInfo findBy(String code, String gamePackageName, String marketName, String env) {
    GiftCode giftCode = giftCodeRepository.findBy(code, gamePackageName, marketName, env);
    if (giftCode == null) {
      throw new NotFoundException();
    }

    return GiftCodeMapper.toGiftCodeInfo(giftCode);
  }

  @Override
  public GiftCodeInfo update(
      String id,
      String name,
      String code,
      String gamePackageName,
      String env,
      String marketName,
      WalletChange walletChange,
      long expireDate,
      int maxUseCountGlobal,
      boolean isActive,
      int minClientVersion,
      int maxClientVersion,
      int minLevelPermission,
      int daysAfterInstall) {

    gameFeignService.getByPackageNameAndEnv(gamePackageName, env);
    gameFeignService.getMarketByName(marketName);

    return GiftCodeMapper.toGiftCodeInfo(giftCodeRepository.update(
        id,
        name,
        code,
        gamePackageName,
        marketName,
        env,
        walletChange,
        expireDate,
        maxUseCountGlobal,
        isActive,
        minClientVersion,
        maxClientVersion,
        minLevelPermission,
        daysAfterInstall

    ));
  }

  @Override
  public void useGiftCode(
      Integer clientVersion,
      Integer level,
      Integer daysAfterInstall,
      String code,
      String env,
      String gamePackageName,
      String marketName) {
    if (level == null) {
      level = Integer.MAX_VALUE;
    }
    if (daysAfterInstall == null) {
      daysAfterInstall = Integer.MAX_VALUE;
    }
    GiftCodeInfo giftCodeInfo = findBy(code, gamePackageName, marketName, env);

    if (giftCodeInfo.getMinLevelPermission() > level ||
        giftCodeInfo.getDaysAfterInstall() > daysAfterInstall ||
        giftCodeInfo.getMinClientVersion() > clientVersion ||
        giftCodeInfo.getMaxClientVersion() < clientVersion ||
        giftCodeInfo.getMaxUseCountGlobal() <= 0) {
      throw new UseGiftCodeException();
    }

    if (!giftCodeInfo.isActive() ||
        timeService.getNowUnixFromInstantClass() > giftCodeInfo.getExpireDate()) {
      throw new GiftCodeExpireOrNotActiveException();
    }

    giftCodeRepository.useGiftCode(code, gamePackageName, marketName, env);
  }
}

