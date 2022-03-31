package com.mirbozorgi.business.mapper;

import com.mirbozorgi.business.domain.GiftCodeInfo;
import com.mirbozorgi.core.docuemnt.GiftCode;

public class GiftCodeMapper {

  public static GiftCodeInfo toGiftCodeInfo(GiftCode giftCode) {
    return new GiftCodeInfo(
        giftCode.getStringId(),
        giftCode.getName(),
        giftCode.getCode(),
        giftCode.getGamePackageName(),
        giftCode.getEnv(),
        giftCode.getMarketName(),
        giftCode.getWalletChange(),
        giftCode.getCreationDate(),
        giftCode.getExpireDate(),
        giftCode.getMaxUseCountGlobal(),
        giftCode.isActive(),
        giftCode.getMinClientVersion(),
        giftCode.getMaxClientVersion(),
        giftCode.getMinLevelPermission(),
        giftCode.getDaysAfterInstall()
    );
  }

}
