package com.mirbozorgi.business.mapper;

import com.mirbozorgi.business.domain.LevelInfo;
import com.mirbozorgi.core.entity.Level;

public class LevelMapper {

  public static LevelInfo toInfo(Level level) {
    return new LevelInfo(
        level.getId(),
        level.getLevelName(),
        level.getGame().getId(),
        level.getGame().getName(),
        level.getMinXp(),
        level.getMaxXp(),
        level.getLevelNumber()
    );

  }

}
