package com.mirbozorgi.business.service.impl;

import com.mirbozorgi.business.domain.GameInfo;
import com.mirbozorgi.business.exception.NotFoundException;
import com.mirbozorgi.business.exception.PackageNameOrEnvNotFoundException;
import com.mirbozorgi.business.mapper.GameMapper;
import com.mirbozorgi.business.service.GameService;
import com.mirbozorgi.core.entity.Game;
import com.mirbozorgi.core.entity.Market;
import com.mirbozorgi.core.repository.entity.GameRepository;
import com.mirbozorgi.core.repository.entity.MarketRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GameServiceImp implements GameService {

  @Autowired
  private GameRepository gameRepository;

  @Autowired
  private MarketRepository marketRepository;

  @Override
  public GameInfo getById(int id) {
    return GameMapper.toGameInfo(gameRepository.findById(id));
  }

  @Override
  public GameInfo getByPackageNameAndEnv(String packageName, String env) {

    Game game = gameRepository.findByPackageNameAndEnv(packageName, env);
    if (game == null) {
      throw new PackageNameOrEnvNotFoundException();
    }

    return GameMapper.toGameInfo(game);
  }

  @Transactional
  @Override
  public GameInfo save(
      String name,
      String packageName,
      boolean active,
      boolean haveUserLogin,
      String env,
      int lastVersion,
      int supportedVersion,
      String instagram,
      String telegram,
      String facebook,
      String twitter,
      String linkedin,
      List<Market> markets,
      int defualtMarketId,
      int defaultGem,
      int defaultGold,
      int defaultLevel,
      int defaultXp) {
    Market defaultMarket = marketRepository.findById(defualtMarketId);

    if (defaultMarket == null) {
      defualtMarketId = markets.get(0).getId();
    }
    Game game = new Game(
        name,
        packageName,
        active,
        haveUserLogin,
        env,
        lastVersion,
        supportedVersion,
        instagram,
        telegram,
        facebook,
        marketRepository.findById(defualtMarketId),
        twitter,
        linkedin,
        markets,
        defaultGem,
        defaultGold,
        defaultLevel,
        defaultXp);
    return GameMapper.toGameInfo(gameRepository.save(game));
  }

  @Transactional
  @Override
  public GameInfo update(
      int id,
      String name,
      String packageName,
      boolean active,
      boolean haveUserLogin,
      String env, int
      lastVersion,
      int supportedVersion,
      String instagram,
      String telegram,
      String facebook,
      String twitter,
      String linkedin,
      List<Market> markets,
      int defualtMarketId,
      int defaultGem,
      int defaultGold,
      int defaultLevel,
      int defaultXp) {
    Game game = gameRepository.findById(id);

    if (game == null) {
      throw new NotFoundException();
    }

    if (name != null) {
      game.setName(name);
    }

    if (packageName != null) {
      game.setPackageName(packageName);
    }
    if (env != null) {
      game.setEnv(env);
    }
    if (lastVersion != 0) {
      game.setLastVersion(lastVersion);
    }
    if (supportedVersion != 0) {
      game.setSupportedVersion(supportedVersion);
    }
    if (instagram != null) {
      game.setInstagram(instagram);
    }
    if (telegram != null) {
      game.setTelegram(telegram);
    }
    if (facebook != null) {
      game.setFacebook(facebook);
    }

    if (twitter != null) {
      game.setTwitter(twitter);
    }

    if (linkedin != null) {
      game.setLinkedin(linkedin);
    }

    if (markets != null) {
      if (markets.size() != 0) {
        game.setMarkets(markets);
      }
    }

    if (defualtMarketId != 0) {
      Market defaultMarket = marketRepository.findById(defualtMarketId);
      game.setDefaultMarket(defaultMarket);


    }

    game.setDefaultGem(defaultGem);

    game.setDefaultGold(defaultGold);

    game.setDefaultLevel(defaultLevel);

    game.setDefaultXp(defaultXp);

    game.setActive(active);
    game.setHaveUserLogin(haveUserLogin);

    gameRepository.save(game);
    return GameMapper.toGameInfo(game);
  }

  @Override
  public List<GameInfo> getAll() {
    List<Game> all = gameRepository.findAll();
    if (all == null) {
      return new ArrayList<>();
    }
    return all
        .stream()
        .map(game -> GameMapper.toGameInfo(game))
        .collect(Collectors.toList());
  }
}
