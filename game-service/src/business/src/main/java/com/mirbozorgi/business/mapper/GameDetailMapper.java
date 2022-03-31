package com.mirbozorgi.business.mapper;

import com.mirbozorgi.business.domain.GameDetailInfo;
import com.mirbozorgi.core.entity.GameDetail;

public class GameDetailMapper {

  public static GameDetailInfo toGameDetailInfo(GameDetail gameDetail) {

    return new GameDetailInfo(
        gameDetail.getId(),
        gameDetail.getGame().getId(),
        gameDetail.getPublicKey(),
        gameDetail.getPrivateKey(),
        gameDetail.getFailMirrorLink(),
        gameDetail.getSuccessMirrorLink()
    );

  }

}
