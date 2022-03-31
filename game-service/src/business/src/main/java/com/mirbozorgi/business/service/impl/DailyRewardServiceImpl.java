package com.mirbozorgi.business.service.impl;

import com.mirbozorgi.business.exception.NotFoundException;
import com.mirbozorgi.business.service.DailyRewardService;
import com.mirbozorgi.business.domain.DailyRewardInfo;
import com.mirbozorgi.business.mapper.DailyRewardMapper;
import com.mirbozorgi.core.entity.DailyReward;
import com.mirbozorgi.core.entity.Game;
import com.mirbozorgi.core.repository.entity.DailyRewardRepository;
import com.mirbozorgi.core.repository.entity.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DailyRewardServiceImpl implements DailyRewardService {

  @Autowired
  private DailyRewardRepository repository;

  @Autowired
  private GameRepository gameRepository;

  @Transactional
  @Override
  public DailyRewardInfo add(
      int gameId,
      int gem,
      Short level,
      int gold,
      int xp,
      long avatarId,
      long vipPeriodTime,
      Boolean addHami) {
    Game game = gameRepository.findById(gameId);

    DailyReward dailyReward = new DailyReward(
        game,
        gem,
        level,
        gold,
        xp,
        avatarId,
        vipPeriodTime,
        addHami
    );
    DailyReward add = repository.add(dailyReward);

    return DailyRewardMapper.toInfo(add);
  }

  @Transactional
  @Override
  public DailyRewardInfo update(
      int id,
      int gameId,
      int gem,
      Short level,
      int gold,
      int xp,
      long avatarId,
      long vipPeriodTime,
      Boolean addHami) {

    DailyReward dailyRewardFounded = repository.findById(id);

    if (gem != 0) {
      dailyRewardFounded.setGem(gem);
    }

    if (level != null) {
      dailyRewardFounded.setLevel(level);
    }

    if (gold != 0) {
      dailyRewardFounded.setGold(gold);
    }

    if (xp != 0) {
      dailyRewardFounded.setXp(xp);
    }

    if (avatarId != 0) {
      dailyRewardFounded.setAvatarId(avatarId);
    }

    if (vipPeriodTime != 0) {
      dailyRewardFounded.setVipPeriodTime(vipPeriodTime);
    }
    if (addHami != null) {
      dailyRewardFounded.setAddHami(addHami);
    }

    return DailyRewardMapper.toInfo(repository.add(dailyRewardFounded));
  }

  @Override
  public DailyRewardInfo findById(int id) {

    DailyReward founded = repository.findById(id);

    if (founded == null) {
      throw new NotFoundException();
    }
    return DailyRewardMapper.toInfo(founded);
  }

  @Override
  public DailyRewardInfo findBy(String gamePackageName, String env) {
    DailyReward founded = repository.findBy(gamePackageName, env);
    if (founded == null) {
      throw new NotFoundException();
    }
    return DailyRewardMapper.toInfo(founded);
  }

  @Transactional
  @Override
  public void delete(int id) {
    repository.delete(id);
  }
}
