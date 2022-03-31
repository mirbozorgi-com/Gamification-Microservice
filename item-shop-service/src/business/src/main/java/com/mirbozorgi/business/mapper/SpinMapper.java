package com.mirbozorgi.business.mapper;

import com.mirbozorgi.business.domain.SpinInfo;
import com.mirbozorgi.core.docuemnt.Spin;

public class SpinMapper {

  public static SpinInfo toInfo(Spin spin) {
    return new SpinInfo(
        spin.getStringId(),
        spin.getGamePackageName(),
        spin.getEnv(),
        spin.getMarket()
    );

  }

}
