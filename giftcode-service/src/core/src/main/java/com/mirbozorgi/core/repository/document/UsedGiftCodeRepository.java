package com.mirbozorgi.core.repository.document;

import com.mirbozorgi.core.docuemnt.UsedGiftCode;
import java.util.List;

public interface UsedGiftCodeRepository {

  UsedGiftCode addUsedGiftCode(UsedGiftCode usedGiftCode);

  UsedGiftCode getBy(
      String code,
      String gamePackageName,
      String env,
      String marketName,
      String userUuId);


  List<UsedGiftCode> getAll(
      String code,
      String gamePackageName,
      String env,
      String marketName,
      String userUuId);
}
