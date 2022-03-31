package com.mirbozorgi.business.service;

import com.mirbozorgi.business.domain.LevelInfo;
import java.util.List;

public interface LevelService {

  LevelInfo findById(int id);

  LevelInfo findByGameIdAndLimitation(
      String packageName,
      String env,
      int currentXp
  );

  LevelInfo save(
      String levelName,
      int gameId,
      int minXp,
      int maxXp,
      int levelNumber);

  List<LevelInfo> findAll(Integer gameId);

  void delete(int id);

  LevelInfo update(
      int id,
      String levelName,
      Integer minXp,
      Integer maxXp,
      Integer levelNumber);
}
