package com.mirbozorgi.business.service.impl;

import com.mirbozorgi.business.service.TimeService;
import com.mirbozorgi.business.service.PlayerGemBuyService;
import com.mirbozorgi.core.domain.PlayerGemBuyData;
import com.mirbozorgi.core.repository.document.PlayerGemBuyRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerGemBuyServiceImpl implements PlayerGemBuyService {

  @Autowired
  private PlayerGemBuyRepository repository;
  @Autowired
  private TimeService timeService;


  @Override
  public void update(
      String uuid,
      String gamePackageName,
      String env,
      String marketName,
      int gem,
      String username,
      List<Integer> avatarIds,
      int level,
      long endVipTime) {

    PlayerGemBuyData playerGemBuyData = new PlayerGemBuyData(
        gem,
        timeService.getNowUnixFromInstantClass(),
        username,
        avatarIds,
        level,
        endVipTime
    );

    repository.upsert(
        uuid,
        gamePackageName,
        env,
        marketName,
        playerGemBuyData
    );
  }
}
