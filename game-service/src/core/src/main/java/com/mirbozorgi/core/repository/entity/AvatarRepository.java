package com.mirbozorgi.core.repository.entity;

import com.mirbozorgi.core.entity.Avatar;
import java.util.List;

public interface AvatarRepository {

  Avatar save(Avatar avatar);

  Avatar findById(long id);

  Avatar findByNameAndGameId(String name, int gameId);

  List<Avatar> findAll(String gamePackageName, String env);

  void delete(Avatar avatar);
}
