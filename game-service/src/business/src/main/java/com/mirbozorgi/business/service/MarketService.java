package com.mirbozorgi.business.service;

import com.mirbozorgi.business.domain.FindNameMarketData;
import com.mirbozorgi.business.domain.MarketInfo;
import com.mirbozorgi.core.utilities.MarketType;
import java.util.List;

public interface MarketService {

  MarketInfo findById(int id);

  FindNameMarketData findByName(String name);

  MarketInfo findByNameFullResponse(String name);

  void delete(int id);

  MarketInfo save(
      String name,
      MarketType type,
      String clientId,
      String clientSecret,
      String refreshToken,
      String redirectUrl,
      boolean byPass,
      boolean justLengthCheck,
      byte maxLengthVerification,
      boolean active,
      String verificationUrl,
      String refreshTokenUrl,
      int refreshTokenTimeOut,
      String accessToken,
      String marketUrl,
      String telegramUrl,
      String instagramUrl,
      int lastVersion,
      int supportedVersion);

  List<MarketInfo> findAll();

}
