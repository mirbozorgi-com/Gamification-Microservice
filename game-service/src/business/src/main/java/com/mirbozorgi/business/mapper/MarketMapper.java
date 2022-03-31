package com.mirbozorgi.business.mapper;

import com.mirbozorgi.business.domain.MarketInfo;
import com.mirbozorgi.core.entity.Market;
import java.util.stream.Collectors;

public class MarketMapper {

  public static MarketInfo toMarketInfo(Market market) {

    return new MarketInfo(
        market.getId(),
        market.getName(),
        market.getMarketType().toString(),
        market.getClientId(),
        market.getClientSecret(),
        market.getRefreshToken(),
        market.getRedirectUrl(),
        market.isByPass(),
        market.isJustLengthCheck(),
        market.getMaxLengthVerification(),
        market.isActive(),
        market.getVerificationUrl(),
        market.getRefreshTokenUrl(),
        market.getRefreshTokenTimeOut(),
        market.getAccessToken(),
        market.getMarketUrl(),
        market.getTelegramUrl(),
        market.getInstagramUrl(),
        market.getLastVersion(),
        market.getSupportedVersion(),
        market.getGames().stream().map(urEntity -> urEntity.getId()).collect(
            Collectors.toList()),
        market.getGames().stream().map(urEntity -> urEntity.getPackageName()).collect(
            Collectors.toList())

    );


  }

}
