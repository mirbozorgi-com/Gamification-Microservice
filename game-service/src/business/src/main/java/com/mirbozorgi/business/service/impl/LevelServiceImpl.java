package com.mirbozorgi.business.service.impl;

import com.mirbozorgi.business.domain.LevelInfo;
import com.mirbozorgi.business.exception.NotFoundException;
import com.mirbozorgi.business.mapper.LevelMapper;
import com.mirbozorgi.business.service.LevelService;
import com.mirbozorgi.core.entity.Game;
import com.mirbozorgi.core.entity.Level;
import com.mirbozorgi.core.repository.entity.GameRepository;
import com.mirbozorgi.core.repository.entity.LevelRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LevelServiceImpl implements LevelService {

  @Autowired
  private LevelRepository levelRepository;

  @Autowired
  private GameRepository gameRepository;

  @Override
  public LevelInfo findById(int id) {
    Level levelFounded = levelRepository.findById(id);
    if (levelFounded == null) {
      throw new NotFoundException();
    }
    return LevelMapper.toInfo(levelFounded);
  }

  @Override
  public LevelInfo findByGameIdAndLimitation(
      String packageName,
      String env,
      int currentXp
  ) {
    Level levelFounded = levelRepository.findByGameIdAndLimitation(
        packageName,
        env,
        currentXp
    );
    if (levelFounded == null) {
      throw new NotFoundException();
    }

    return LevelMapper.toInfo(levelFounded);
  }

  @Transactional
  @Override
  public LevelInfo save(
      String levelName,
      int gameId,
      int minXp,
      int maxXp,
      int levelNumber) {
    Game game = gameRepository.findById(gameId);
    Level level = new Level(
        levelName,
        levelNumber,
        game,
        minXp,
        maxXp
    );

    return LevelMapper.toInfo(levelRepository.save(level));
  }

  @Override
  public List<LevelInfo> findAll(Integer gameId) {
    List<LevelInfo> levelInfos = new ArrayList<>();
    List<Level> levels = levelRepository.findAll(gameId);
    if (levels == null) {
      return new ArrayList<>();
    }
    for (Level level : levels) {
      levelInfos.add(LevelMapper.toInfo(level));
    }

    return levelInfos;
  }

  @Transactional
  @Override
  public void delete(int id) {
    levelRepository.delete(id);
  }

  @Transactional
  @Override
  public LevelInfo update(
      int id,
      String levelName,
      Integer minXp,
      Integer maxXp,
      Integer levelNumber) {
    Level levelFounded = levelRepository.findById(id);

    if (levelFounded == null) {
      throw new NotFoundException();
    }

    if (levelName != null) {
      levelFounded.setLevelName(levelName);
    }
    if (minXp != null) {
      levelFounded.setMinXp(minXp);

    }

    if (maxXp != null) {
      levelFounded.setMaxXp(maxXp);

    }

    if (levelNumber != null) {
      levelFounded.setLevelNumber(levelNumber);

    }

    return LevelMapper.toInfo(levelRepository.save(levelFounded));
  }
}
