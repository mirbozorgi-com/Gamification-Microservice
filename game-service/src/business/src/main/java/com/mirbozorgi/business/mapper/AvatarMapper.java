package com.mirbozorgi.business.mapper;

import com.mirbozorgi.business.domain.AvatarInfo;
import com.mirbozorgi.core.entity.Avatar;

public class AvatarMapper {

  public static AvatarInfo toInfo(Avatar avatar) {

    return new AvatarInfo(
        avatar.getId(),
        avatar.getGame().getId(),
        avatar.getName(),
        avatar.getAmount(),
        avatar.getCurrencyType(),
        avatar.getActive(),
        avatar.getAvatarType(),
        avatar.getFree(),
        avatar.getAvatarBuyType()
    );
  }

}
