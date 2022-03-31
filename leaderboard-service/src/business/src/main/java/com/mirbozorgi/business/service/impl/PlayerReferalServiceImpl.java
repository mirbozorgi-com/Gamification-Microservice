package com.mirbozorgi.business.service.impl;

import com.mirbozorgi.business.service.PlayerReferalService;
import com.mirbozorgi.core.domain.PlayerRefralData;
import com.mirbozorgi.core.repository.document.PlayerRefralRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerReferalServiceImpl implements PlayerReferalService {

  @Autowired
  private PlayerRefralRepository repository;


  @Override
  public void update(
      String uuid,
      String gamePackageName,
      String env,
      String marketName,
      int quantity,
      int level,
      String username,
      List<Integer> avatarActiveIds,
      long endVipTime) {

    PlayerRefralData playerRefralData = new PlayerRefralData(
        quantity,
        username,
        avatarActiveIds,
        level,
        endVipTime
    );
    repository.upsert(
        uuid,
        gamePackageName,
        env,
        marketName,
        playerRefralData
    );

  }
}
