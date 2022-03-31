package com.mirbozorgi.core.repository.entity.impl;

import com.mirbozorgi.core.entity.Avatar;
import com.mirbozorgi.core.repository.entity.AvatarRepository;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class AvatarRepositoryImpl extends CustomRepository implements AvatarRepository {

  @Override
  public Avatar save(Avatar avatar) {
    return save(Avatar.class, avatar);
  }

  @Override
  public Avatar findById(long id) {
    return findById(Avatar.class, id);
  }

  @Override
  public Avatar findByNameAndGameId(String name, int gameId) {
    return findQueryWrapper(entityManager
        .createQuery("select s from Avatar s where s.name = :name and s.game.id= :gameId",
            Avatar.class)
        .setParameter("name", name)
        .setParameter("gameId", gameId));
  }

  @Override
  public List<Avatar> findAll(String gamePackageName, String env) {
    return listQueryWrapper(
        entityManager
            .createQuery(
                "select s from Avatar s where s.game.packageName = :gamePackageName and s.game.env=:env",
                Avatar.class)
            .setParameter("env", env)
            .setParameter("gamePackageName", gamePackageName)
    );
  }

  @Override
  public void delete(Avatar avatar) {
    delete(Avatar.class, avatar);
  }
}
