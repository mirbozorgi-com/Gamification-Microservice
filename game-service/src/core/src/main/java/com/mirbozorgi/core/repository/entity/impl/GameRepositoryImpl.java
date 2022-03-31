package com.mirbozorgi.core.repository.entity.impl;

import com.mirbozorgi.core.entity.Game;
import com.mirbozorgi.core.repository.entity.GameRepository;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class GameRepositoryImpl extends CustomRepository implements GameRepository {


  @Override
  public Game findById(int id) {
    return findById(Game.class, id);
  }


  @Override
  public Game findByPackageNameAndEnv(String packageName, String env) {
    return findQueryWrapper(entityManager
        .createQuery("select g from Game g where g.packageName= :packageName and g.env= :env",
            Game.class)
        .setParameter("packageName", packageName)
        .setParameter("env", env));


  }

  @Override
  public Game save(Game game) {
    return save(Game.class, game);
  }

  @Override
  public List<Game> findAll() {
    return listQueryWrapper(entityManager.createQuery(
        "select g from Game g order by g.id desc ",
        Game.class));
  }
}
