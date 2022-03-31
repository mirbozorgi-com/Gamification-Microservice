package com.mirbozorgi.business.mapper;

import com.mirbozorgi.business.domain.GiftCodeUseData;
import com.mirbozorgi.core.docuemnt.UsedGiftCode;
import mirbozorgi.base.domain.user.WalletChange;

public class GiftCodeUsedMapper {

  public static GiftCodeUseData toGiftCodeUsedData(UsedGiftCode usedGiftCode,
      WalletChange walletChange) {

    return new GiftCodeUseData(
        usedGiftCode.getStringId(),
        usedGiftCode.getUserUuId(),
        usedGiftCode.getCode(),
        usedGiftCode.getUsedDate(),
        usedGiftCode.getGamePackageName(),
        usedGiftCode.getEnv(),
        usedGiftCode.getMarketName(),
        walletChange
    );


  }

}
