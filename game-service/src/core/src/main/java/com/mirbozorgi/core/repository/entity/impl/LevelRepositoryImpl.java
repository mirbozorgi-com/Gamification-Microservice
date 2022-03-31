package com.mirbozorgi.core.repository.entity.impl;

import com.mirbozorgi.core.entity.Level;
import com.mirbozorgi.core.repository.entity.LevelRepository;
import java.util.List;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

@Repository
public class LevelRepositoryImpl extends CustomRepository implements LevelRepository {

  @Override
  public Level findById(int id) {
    return findById(Level.class, id);
  }

  @Override
  public Level findByGameIdAndLimitation(
      String packageName,
      String env,
      int currentXp
  ) {
    Level level = findQueryWrapper(
        entityManager
            .createQuery("select s from Level s "
                    + "where  s.game.packageName = :packageName "
                    + "and s.game.env=:env "
                    + "and :currentXp BETWEEN s.minXp AND s.maxXp",
                Level.class)
            .setParameter("env", env)
            .setParameter("packageName", packageName)
            .setParameter("currentXp", currentXp));
    if (level == null) {
      Query query = entityManager
          .createQuery(
              "select s from Level s "
                  + "where  s.game.packageName = :packageName "
                  + "and s.game.env=:env "
                  + "  order by s.maxXp desc  ").setMaxResults(1);

      query.setParameter("packageName", packageName);
      query.setParameter("env", env);

      Object o = query.getResultList().get(0);
      level = (Level) o;
    }
    return level;
  }

  @Override
  public Level save(Level level) {
    return save(Level.class, level);
  }

  @Override
  public List<Level> findAll(Integer gameId) {
    return listQueryWrapper(
        entityManager
            .createQuery("select s from Level s where :gameId is null or s.game.id = :gameId",
                Level.class)
            .setParameter("gameId", gameId)
    );
  }

  @Override
  public void delete(int id) {
    Level level = findById(id);
    delete(Level.class, level);
  }
}
