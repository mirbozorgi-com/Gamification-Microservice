package com.mirbozorgi.business.service.impl;

import com.mirbozorgi.business.exception.MarketException;
import com.mirbozorgi.business.exception.NotFoundException;
import com.mirbozorgi.business.service.ConstCohortService;
import com.mirbozorgi.business.domain.ChooseCohortData;
import com.mirbozorgi.business.domain.CohortData;
import com.mirbozorgi.business.domain.ConstCohortData;
import com.mirbozorgi.business.domain.GetConfigCohortData;
import com.mirbozorgi.business.mapper.CohortMapper;
import com.mirbozorgi.business.service.CohortService;
import com.mirbozorgi.business.service.RandomService;
import com.mirbozorgi.business.service.SerializerService;
import com.mirbozorgi.core.entity.Cohort;
import com.mirbozorgi.core.repository.document.CohortRepository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import mirbozorgi.base.domain.game.GameInfo;
import mirbozorgi.base.feignService.GameFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CohortServiceImpl implements CohortService {

  @Autowired
  private CohortRepository cohortRepository;

  @Autowired
  private GameFeignService gameFeignService;

  @Autowired
  private SerializerService serializerService;

  @Autowired
  private RandomService randomService;

  @Autowired
  private ConstCohortService constCohortService;

  @Value("${cohort.default-name}")
  String defaultCohort;

  @Value("${market.default-name}")
  String defaultMarket;


  @Override
  public CohortData save(
      String gamePackageName,
      String env,
      String name,
      String market,
      int chance,
      boolean active,
      String constCohort,
      Object config,
      String defaultCohort,
      int minVersionClient,
      int maxVersionClient) {

    if (maxVersionClient == 0) {

      maxVersionClient = Integer.MAX_VALUE;

    }
    //request to game-service to find packageName game

    GameInfo gameInfo = gameFeignService
        .getByPackageNameAndEnv(gamePackageName, env);

    if (!gameInfo.getMarketNames().contains(market)) {
      throw new MarketException();
    }

    Cohort cohort = cohortRepository.save(new Cohort(
        gamePackageName,
        name,
        chance,
        active,
        constCohort,
        market,
        config,
        defaultCohort,
        minVersionClient,
        maxVersionClient
    ));

    return CohortMapper.toCohortData(
        cohort.getStringId(),
        cohort.getGamePackageName(),
        cohort.getName(),
        cohort.getChance(),
        cohort.isActive(),
        cohort.getConstCohort(),
        cohort.getConfig(),
        cohort.getDefaultCohort(),
        cohort.getMinVersionClient(),
        cohort.getMaxVersionClient()
    );
  }

  @Override
  public void delete(String id) {
    Cohort cohort = cohortRepository.findById(id);
    if (cohort == null) {
      throw new NotFoundException();
    }
    cohortRepository.delete(cohort);
  }

  @Override
  public CohortData update(
      String id,
      String gamePackageName,
      String env,
      String name,
      String market,
      int chance,
      boolean active,
      String constCohort,
      Object config,
      String defaultCohort,
      int minVersionClient,
      int maxVersionClient) {
    if (cohortRepository.findById(id) == null) {
      throw new NotFoundException();
    }
    if (maxVersionClient == 0) {

      maxVersionClient = Integer.MAX_VALUE;

    }

    //request to game-service to find packageName game
    GameInfo gameInfo = serializerService
        .toObjectFromFeign(gameFeignService.getByPackageNameAndEnv(gamePackageName, env),
            GameInfo.class);

    if (!gameInfo.getMarketNames().contains(market)) {
      throw new MarketException();
    }

    Cohort cohort = cohortRepository.update(
        id,
        gamePackageName,
        name,
        market,
        chance,
        active,
        constCohort,
        config,
        defaultCohort,
        minVersionClient,
        maxVersionClient

    );
    return CohortMapper.toCohortData(
        cohort.getStringId(),
        cohort.getGamePackageName(),
        cohort.getName(),
        cohort.getChance(),
        cohort.isActive(),
        cohort.getConstCohort(),
        cohort.getConfig(),
        cohort.getDefaultCohort(),
        cohort.getMinVersionClient(),
        cohort.getMaxVersionClient()
    );
  }

  @Override
  public CohortData findById(String id) {

    Cohort cohort = cohortRepository.findById(id);
    if (cohort == null) {
      throw new NotFoundException();
    }

    return CohortMapper.toCohortData(
        cohort.getStringId(),
        cohort.getGamePackageName(),
        cohort.getName(),
        cohort.getChance(),
        cohort.isActive(),
        cohort.getConstCohort(),
        cohort.getConfig(),
        cohort.getDefaultCohort(),
        cohort.getMinVersionClient(),
        cohort.getMaxVersionClient()
    );
  }

  @Override
  public CohortData findByNameAndPackageNameAndMarket(
      String name,
      String packageName,
      String market) {

    Cohort cohort = cohortRepository.findByNameAndPackageNameAndMarket(name, packageName, market);
    if (cohort == null) {
      throw new NotFoundException();
    }

    return CohortMapper.toCohortData(
        cohort.getStringId(),
        cohort.getGamePackageName(),
        cohort.getName(),
        cohort.getChance(),
        cohort.isActive(),
        cohort.getConstCohort(),
        cohort.getConfig(),
        cohort.getDefaultCohort(),
        cohort.getMinVersionClient(),
        cohort.getMaxVersionClient()
    );
  }

  @Override
  public List<CohortData> findAll() {

    List<CohortData> cohortDatas = new ArrayList<>();

    for (Cohort cohort : cohortRepository.findAll()) {
      cohortDatas.add(CohortMapper.toCohortData(
          cohort.getStringId(),
          cohort.getGamePackageName(),
          cohort.getName(),
          cohort.getChance(),
          cohort.isActive(),
          cohort.getConstCohort(),
          cohort.getConfig(),
          cohort.getDefaultCohort(),
          cohort.getMinVersionClient(),
          cohort.getMaxVersionClient()
      ));
    }

    return cohortDatas;
  }

  @Override
  public ChooseCohortData chooseRandom(
      int clientVersion,
      String packageName,
      String env,
      String market,
      String cohort) {

    String cohortName = "";
    GameInfo gameInfo = gameFeignService.getByPackageNameAndEnv(packageName, env);

    if (market == null) {
      market = defaultMarket;
    } else {
      if (!gameInfo.getMarketNames().contains(market)) {
        market = defaultMarket;

      }
    }
    //offline cohort:
    if (cohort != null) {
      if (!cohort.equals("")) {
        return new ChooseCohortData(
            cohort,
            packageName,
            market
        );
      }

    }
//random cohort:

    List<Cohort> cohorts = cohortRepository.findAllByGame(packageName);

    String finalMarket = market;
    cohorts
        .stream()
        .filter(i -> i.isActive()).filter(i -> i.getMarket().equals(finalMarket))
        .collect(Collectors.toList());

    int[] chances = cohorts.stream().mapToInt(i -> i.getChance()).toArray();

    int index = randomService.nextInt(chances) - 1;

    if (index < 0) {
      cohortName = defaultCohort;


    } else {
      cohortName = cohorts.get(index).getName();
      if (clientVersion <= cohorts.get(index).getMinVersionClient() &&
          clientVersion >= cohorts.get(index).getMaxVersionClient()) {
        cohortName = cohorts.get(index).getDefaultCohort();
      }


    }

    return new ChooseCohortData(
        cohortName,
        packageName,
        market
    );

  }

  @Override
  public GetConfigCohortData getConfigCohort(
      String packageName,
      String cohortName,
      String marketName) throws IOException {

    CohortData cohortData = findByNameAndPackageNameAndMarket(cohortName, packageName, marketName);
    Object cohortConfig = cohortData.getConfig();
    String cohortJson = serializerService.toJson(cohortConfig);

    ConstCohortData constCohortData = constCohortService.findByName(cohortData.getConstCohort());
    Object constCohortConfig = constCohortData.getConfig();
    String constCohortJson = serializerService.toJson(constCohortConfig);

    String mergedJson = serializerService.mergeJson(cohortJson, constCohortJson);

    return new GetConfigCohortData(
        cohortName,
        serializerService.toObj(mergedJson, Object.class)
    );
  }

}
