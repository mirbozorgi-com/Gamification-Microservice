package com.mirbozorgi.business.service.impl;

import com.mirbozorgi.business.domain.FindNameMarketData;
import com.mirbozorgi.business.domain.MarketInfo;
import com.mirbozorgi.business.exception.NotFoundException;
import com.mirbozorgi.business.mapper.MarketMapper;
import com.mirbozorgi.business.service.MarketService;
import com.mirbozorgi.core.entity.Market;
import com.mirbozorgi.core.repository.entity.MarketRepository;
import com.mirbozorgi.core.utilities.MarketType;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MarketServiceImpl implements MarketService {

  @Autowired
  private MarketRepository marketRepository;

  @Override
  public MarketInfo findById(int id) {
    return MarketMapper.toMarketInfo(marketRepository.findById(id));
  }

  @Override
  public FindNameMarketData findByName(String name) {
    Market market = marketRepository.findByName(name);
    if (market == null) {
      throw new NotFoundException();
    }
    return new FindNameMarketData(
        market.getName(),
        market.getLastVersion(),
        market.getSupportedVersion()
    );
  }

  @Transactional
  @Override
  public MarketInfo findByNameFullResponse(String name) {
    Market market = marketRepository.findByName(name);
    if (market == null) {
      throw new NotFoundException();
    }
    return MarketMapper.toMarketInfo(market);

  }

  @Transactional
  @Override
  public void delete(int id) {
    marketRepository.delete(id);
  }

  @Transactional
  @Override
  public MarketInfo save(
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
      int supportedVersion) {

    Market market = new Market(
        name,
        type,
        clientId,
        clientSecret,
        refreshToken,
        redirectUrl,
        byPass,
        justLengthCheck,
        maxLengthVerification,
        active,
        verificationUrl,
        refreshTokenUrl,
        refreshTokenTimeOut,
        accessToken,
        marketUrl,
        telegramUrl,
        instagramUrl,
        lastVersion,
        supportedVersion,
        new ArrayList<>());

    return MarketMapper.toMarketInfo(marketRepository.save(market));
  }

  @Override
  public List<MarketInfo> findAll() {
    List<MarketInfo> marketInfos = new ArrayList<>();
    List<Market> markets = marketRepository.findAll();
    for (Market market : markets) {
      marketInfos.add(MarketMapper.toMarketInfo(market));
    }
    return marketInfos;
  }
}
