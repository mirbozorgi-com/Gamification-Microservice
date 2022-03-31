package com.mirbozorgi.core.repository.document.impl;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import com.mirbozorgi.core.repository.document.CustomMongoRepository;
import com.mirbozorgi.core.repository.document.UsedGiftCodeRepository;
import com.mirbozorgi.core.docuemnt.UsedGiftCode;
import java.util.ArrayList;
import java.util.List;
import net.logstash.logback.encoder.org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.stereotype.Repository;

@Repository
public class UsedGiftCodeRepositoryImpl implements UsedGiftCodeRepository {

  @Autowired
  private CustomMongoRepository repository;

  @Override
  public UsedGiftCode addUsedGiftCode(UsedGiftCode usedGiftCode) {
    return repository.add(usedGiftCode);
  }

  @Override
  public UsedGiftCode getBy(
      String code,
      String gamePackageName,
      String env,
      String marketName,
      String userUuId) {

    return repository.fetchFirst(
        UsedGiftCode.class,
        new String[]{"code",
            "usedDate",
            "gamePackageName",
            "userUuId",
            "env",
            "marketName"},
        Criteria.where("userUuId").is(userUuId)
            .and("code").is(code)
            .and("gamePackageName").is(gamePackageName)
            .and("marketName").is(marketName)
            .and("env").is(env)
    );

  }

  @Override
  public List<UsedGiftCode> getAll(
      String code,
      String gamePackageName,
      String env,
      String marketName,
      String userUuId) {
    ArrayList<CriteriaDefinition> where = new ArrayList<>();

    if (code == null) {
      code = "";
    }

    if (userUuId == null) {
      userUuId = "";
    }

    if (env == null) {
      env = "";
    }

    if (gamePackageName == null) {
      gamePackageName = "";
    }

    if (marketName == null) {
      marketName = "";
    }

    if (!StringUtils.isEmpty(code)) {
      where.add(where("code").is(code));
    }

    if (!StringUtils.isEmpty(userUuId)) {
      where.add(where("userUuId").is(userUuId));
    }

    if (!StringUtils.isEmpty(gamePackageName)) {
      where.add(where("gamePackageName").is(gamePackageName));
    }

    if (!StringUtils.isEmpty(env)) {
      where.add(where("env").is(env));
    }

    if (!StringUtils.isEmpty(marketName)) {
      where.add(where("marketName").is(marketName));
    }

    return repository.fetch(
        UsedGiftCode.class,
        new String[]{
            "code",
            "usedDate",
            "gamePackageName",
            "userUuId",
            "env",
            "marketName"},
        null,
        100,
        where.toArray(new CriteriaDefinition[where.size()])
    );
  }
}
