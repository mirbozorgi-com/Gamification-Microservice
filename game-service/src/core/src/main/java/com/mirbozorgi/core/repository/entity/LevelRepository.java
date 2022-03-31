package com.mirbozorgi.core.repository.entity;

import com.mirbozorgi.core.entity.Level;
import java.util.List;

public interface LevelRepository {

  Level findById(int id);

  Level findByGameIdAndLimitation(
      String packageName,
      String env,
      int currentXp
  );

  Level save(Level level);

  List<Level> findAll(Integer gameId);

  void delete(int id);
}
