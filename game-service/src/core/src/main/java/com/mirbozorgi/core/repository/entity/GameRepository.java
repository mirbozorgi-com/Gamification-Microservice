package com.mirbozorgi.core.repository.entity;

import com.mirbozorgi.core.entity.Game;
import java.util.List;

public interface GameRepository {

  Game findById(int id);

  Game findByPackageNameAndEnv(String packageName, String env);

  Game save(Game game);

  List<Game> findAll();
}
