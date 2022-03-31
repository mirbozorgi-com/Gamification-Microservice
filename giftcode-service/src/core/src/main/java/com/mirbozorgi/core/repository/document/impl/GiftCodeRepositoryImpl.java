package com.mirbozorgi.core.repository.document.impl;

import com.mirbozorgi.core.repository.document.CustomMongoRepository;
import com.mirbozorgi.core.repository.document.GiftCodeRepository;
import com.mirbozorgi.core.docuemnt.GiftCode;
import java.util.ArrayList;
import java.util.List;
import mirbozorgi.base.domain.user.WalletChange;
import net.logstash.logback.encoder.org.apache.commons.lang.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class GiftCodeRepositoryImpl implements GiftCodeRepository {

  @Autowired
  private CustomMongoRepository repository;

  @Override
  public GiftCode save(GiftCode giftCode) {
    return repository.add(giftCode);
  }

  @Override
  public void delete(GiftCode giftCode) {
    repository.delete(giftCode);
  }

  @Override
  public GiftCode findById(String id) {
    return repository.fetchFirst(GiftCode.class, new ObjectId(id));

  }

  @Override
  public List<GiftCode> findAll(
      String name,
      String code,
      String gamePackageName,
      String env,
      String marketName) {

    ArrayList<CriteriaDefinition> where = new ArrayList<>();

    if (name == null) {
      name = "";
    }

    if (code == null) {
      code = "";
    }

    if (gamePackageName == null) {
      gamePackageName = "";
    }

    if (env == null) {
      env = "";
    }

    if (marketName == null) {
      marketName = "";
    }

    if (!StringUtils.isEmpty(name)) {
      where.add(Criteria.where("name").is(name));
    }

    if (!StringUtils.isEmpty(code)) {
      where.add(Criteria.where("code").is(code));
    }

    if (!StringUtils.isEmpty(gamePackageName)) {
      where.add(Criteria.where("gamePackageName").is(gamePackageName));
    }

    if (!StringUtils.isEmpty(env)) {
      where.add(Criteria.where("env").is(env));
    }

    if (!StringUtils.isEmpty(marketName)) {
      where.add(Criteria.where("marketName").is(marketName));
    }

    return repository.fetch(
        GiftCode.class,
        new String[]{
            "creationDate",
            "daysAfterInstall",
            "minLevelPermission",
            "maxClientVersion",
            "expireDate",
            "maxUseCountGlobal",
            "isActive",
            "name",
            "minClientVersion",
            "code",
            "gamePackageName",
            "env",
            "marketName",
            "walletChange"
        },
        null,
        100,
        where.toArray(new CriteriaDefinition[where.size()])
    );

  }

  @Override
  public GiftCode findBy(String code, String gamePackageName, String marketName, String env) {
    return repository.fetchFirst(
        GiftCode.class,
        new String[]{
            "name",
            "code",
            "gamePackageName",
            "marketName",
            "env",
            "expireDate",
            "maxUseCountGlobal",
            "isActive",
            "minClientVersion",
            "maxClientVersion",
            "minLevelPermission",
            "daysAfterInstall",
            "walletChange"},
        Criteria.where("gamePackageName").is(gamePackageName)
            .and("code").is(code)
            .and("env").is(env)
            .and("marketName").is(marketName));

  }


  @Override
  public GiftCode update(
      String id,
      String name,
      String code,
      String gamePackageName,
      String marketName,
      String env,
      WalletChange walletChange,
      long expireDate,
      int maxUseCountGlobal,
      boolean isActive,
      int minClientVersion,
      int maxClientVersion,
      int minLevelPermission,
      int daysAfterInstall) {

    Update update = new Update();
    update.set("gamePackageName", gamePackageName);
    update.set("env", env);
    update.set("name", name);
    update.set("code", code);
    update.set("marketName", marketName);
    update.set("walletChange", walletChange);
    update.set("expireDate", expireDate);
    update.set("maxUseCountGlobal", maxUseCountGlobal);
    update.set("isActive", isActive);
    update.set("minClientVersion", minClientVersion);
    update.set("maxClientVersion", maxClientVersion);
    update.set("minLevelPermission", minLevelPermission);
    update.set("daysAfterInstall", daysAfterInstall);

    repository.update(GiftCode.class, update, id);
    return findById(id);
  }

  @Override
  public void useGiftCode(
      String code,
      String gamePackageName,
      String marketName,
      String env) {
    repository.update(
        GiftCode.class,
        new Update().inc("maxUseCountGlobal", -1),
        Criteria.where("gamePackageName").is(gamePackageName)
            .and("marketName").is(marketName)
            .and("env").is(env)
    );
  }
}
