package com.mirbozorgi.business.service.impl;

import com.mirbozorgi.business.exception.MatchNotFoundException;
import com.mirbozorgi.business.exception.NotFoundException;
import com.mirbozorgi.business.exception.PlayerHasActiveMatchException;
import com.mirbozorgi.business.exception.WalletLackException;
import com.mirbozorgi.business.mapper.PlayerMatchMapper;
import com.mirbozorgi.business.service.MatchService;
import com.mirbozorgi.business.service.PlayerMatchService;
import com.mirbozorgi.business.service.TimeService;
import com.mirbozorgi.business.domain.MatchData;
import com.mirbozorgi.business.domain.PlayerDataMatch;
import com.mirbozorgi.business.domain.PlayerMatchData;
import com.mirbozorgi.business.exception.PlayerMatchAccessDenied;
import com.mirbozorgi.core.domain.PlayerData;
import com.mirbozorgi.core.entity.PlayerMatch;
import com.mirbozorgi.core.repository.document.PlayerMatchRepository;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;
import mirbozorgi.base.domain.game.WalletChangeModel;
import mirbozorgi.base.domain.hami.CheckVipTimeInfo;
import mirbozorgi.base.domain.user.CurrentLevelInfo;
import mirbozorgi.base.domain.user.Wallet;
import mirbozorgi.base.domain.user.WalletChange;
import mirbozorgi.base.domain.user.WalletWithVipTime;
import mirbozorgi.base.feignService.UserFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PLayerMatchServiceImpl implements PlayerMatchService {

  @Autowired
  private PlayerMatchRepository playerMatchRepository;

  @Autowired
  private MatchService matchService;

  @Autowired
  private TimeService timeService;


  @Autowired
  private UserFeignService userFeignService;

  @Autowired
  private UserHamiFeginService userHamiFeginService;


  @Value("${api.key.walletchange}")
  String apiKeyWalletChange;


  @Override
  public PlayerMatchData join(
      String gamePackageName,
      String market,
      String matchName,
      String env,
      String userUuId,
      String username,
      Object config) {
    PlayerMatch join = null;
    //find match data
    List<MatchData> matchData = matchService.findAll(gamePackageName, env, market, matchName, true);
    if (matchData.size() == 0) {
      throw new MatchNotFoundException();

    }

    MatchData match = matchData.get(0);

    if (match == null) {
      throw new MatchNotFoundException();
    }
/////////////////////////////////////////
    //find playerMatch with empty place
    PlayerMatch playerMatch = playerMatchRepository.findByMatchIdAndEmptyPlace(match.getId());
    if (playerMatch != null) {
      if (playerMatch.getPlayerUuIds().contains(userUuId)) {
        throw new PlayerHasActiveMatchException();
      }

      if (playerMatch.getPlayerMaxQuantity() - playerMatch.getPlayers().size() == 1) {
        playerMatchRepository.updateEmptyPlaceForPlayer(false, playerMatch.getStringId());
      }
      WalletWithVipTime walletWithVipTime = userFeignService.getWallet(match.getGamePackageName(),
          userUuId,
          match.getEnv(),
          match.getMarketName());
      Wallet foundedWallet = walletWithVipTime.getWallet();
      if (foundedWallet.getGem() < match.getGemCost() || foundedWallet.getGold() < match
          .getGoldCost()) {
        throw new WalletLackException();
      }
      WalletChange wallet = userFeignService.walletChange(
          apiKeyWalletChange,
          new WalletChangeModel(
              -(match.getGemCost()),
              -(match.getGoldCost()),
              Short.parseShort("0"),
              0,
              new ArrayList<>(),
              0,
              false,
              0
          ),
          gamePackageName,
          userUuId,
          env,
          matchName
      );

      join = playerMatchRepository.join(
          playerMatch.getStringId(),
          userUuId,
          username,
          timeService.getNowUnixFromInstantClass(),
          0,
          false,
          false,
          config);

      userFeignService.updateUserStatistic(
          userUuId,
          match.getGamePackageName(),
          match.getEnv(),
          match.getMarketName(),
          0,
          1,
          0,
          match.getGemCost(),
          0,
          0
      );

      return PlayerMatchMapper.playerMatchData(join, match.getConfig(), wallet, 0);
    }
//////////////////////////////////////////
    //make new match
    PlayerData playerData = new PlayerData(
        userUuId,
        username,
        timeService.getNowUnixFromInstantClass(),
        0,
        false,
        false,
        config);

    List<PlayerData> playerDatas = new ArrayList<>();
    playerDatas.add(playerData);

    List<String> userUuIds = new ArrayList<>();
    userUuIds.add(userUuId);

    WalletChange wallet = userFeignService.walletChange(
        apiKeyWalletChange,
        new WalletChangeModel(
            -(match.getGemCost()),
            -(match.getGoldCost()),
            Short.parseShort("0"),
            0,
            new ArrayList<>(),
            0,
            false,
            0
        ),
        gamePackageName,
        userUuId,
        env,
        matchName
    );

    join = new PlayerMatch(
        match.getId(),
        playerDatas,
        userUuIds,
        match.getMaxPlayerInMatch(),
        true
    );
    userFeignService.updateUserStatistic(
        userUuId,
        match.getGamePackageName(),
        match.getEnv(),
        match.getMarketName(),
        0,
        1,
        0,
        match.getGemCost(),
        0,
        0
    );
    return PlayerMatchMapper
        .playerMatchData(playerMatchRepository.save(join), match.getConfig(), wallet);

  }

  @Override
  public PlayerMatchData offlineJoin(
      String userUuId,
      Object config,
      int numberOfPlayerWanted,
      String matchId,
      int currentLevel,
      List<Integer> currentAvatarIds,
      String username) {
    int maxXp = Integer.MAX_VALUE;
    int minXp = 0;

    //find match data
    MatchData match = matchService.findById(matchId);

    if (match == null) {
      throw new MatchNotFoundException();
    }
    WalletWithVipTime walletWithVipTime = userFeignService.getWallet(match.getGamePackageName(),
        userUuId,
        match.getEnv(),
        match.getMarketName());
    Wallet foundedWallet = walletWithVipTime.getWallet();
////////////add player data to players/////////////////
    Wallet walletFounded = null;
    String userNameFinal = null;
    try {
      WalletWithVipTime wallet = userFeignService.getWallet(
          match.getGamePackageName(),
          userUuId,
          match.getEnv(),
          match.getMarketName()
      );
      walletFounded = wallet.getWallet();
      userNameFinal = username;
      maxXp = match.getMaxXpForJoinOffline() + walletFounded.getXp();
      minXp = walletFounded.getXp() - match.getMinXpForJoinOffline();
    } catch (Exception e) {
      userNameFinal = "";
    }

    List<PlayerData> playerDatas = new ArrayList<>();
    playerDatas.add(new PlayerData(
        userUuId,
        userNameFinal,
        timeService.getNowUnixFromInstantClass(),
        0,
        false,
        false,
        config,
        currentLevel,
        currentAvatarIds,
        walletWithVipTime.getEndVipTime()
    ));

    List<String> userUuIds = new ArrayList<>();
    userUuIds.add(userUuId);
///////////////////////find fake players by xp level////////////////////////////
    ArrayList allByXp =
        userFeignService.findAllByXp(
            maxXp,
            minXp,
            match.getGamePackageName(),
            match.getEnv(),
            match.getMarketName(),
            userUuId);
/////////////////add randomly fake players///////////////////////
    if (allByXp.size() >= numberOfPlayerWanted) {
      for (int i = 0; i < numberOfPlayerWanted; i++) {
        Random ran = new Random();
        int index = ran.nextInt(allByXp.size());

        Object o = allByXp.get(index);
        PlayerDataMatch playerDataMatch = toDataMatch(o);

        String userUuIdFounded = playerDataMatch.getUserUuId();
        int currentLevelFounded = playerDataMatch.getCurrentLevel();
        List<Integer> currentAvatarIdsFounded = playerDataMatch.getCurrentAvatarIds();
        String usernameFounded = playerDataMatch.getUsername();

        long finishVipTime = 0;
        CheckVipTimeInfo checkVipTimeInfo = userHamiFeginService.checkVipTime(
            userUuIdFounded,
            match.getGamePackageName(),
            match.getEnv(),
            match.getMarketName()
        );
        if (checkVipTimeInfo != null) {
          finishVipTime = checkVipTimeInfo.getFinishTime();
        }

        allByXp.remove(index);

        userUuIds.add(userUuIdFounded);

        playerDatas.add(new PlayerData(
            userUuIdFounded,
            usernameFounded,
            0,
            0,
            false,
            false,
            config,
            currentLevelFounded,
            currentAvatarIdsFounded,
            finishVipTime
        ));
      }
    }

    ///if notFound -- find with no limit for xp
    else {
      ArrayList allByXpAll =
          userFeignService.findAllByXp(
              Integer.MAX_VALUE,
              0,
              match.getGamePackageName(),
              match.getEnv(),
              match.getMarketName(),
              userUuId);
      for (int i = 0; i < numberOfPlayerWanted; i++) {
        Random ran = new Random();
        int index = ran.nextInt(allByXpAll.size());

        Object o = allByXpAll.get(index);

        PlayerDataMatch playerDataMatch = toDataMatch(o);

        String userUuIdFounded = playerDataMatch.getUserUuId();
        int currentLevelFounded = playerDataMatch.getCurrentLevel();
        List<Integer> currentAvatarIdsFounded = playerDataMatch.getCurrentAvatarIds();
        String usernameFounded = playerDataMatch.getUsername();
        long finishVipTime = 0;
        CheckVipTimeInfo checkVipTimeInfo = userHamiFeginService.checkVipTime(
            userUuIdFounded,
            match.getGamePackageName(),
            match.getEnv(),
            match.getMarketName()
        );
        if (checkVipTimeInfo != null) {
          finishVipTime = checkVipTimeInfo.getFinishTime();
        }
        allByXpAll.remove(index);

        userUuIds.add(userUuIdFounded);

        playerDatas.add(new PlayerData(
            userUuIdFounded,
            usernameFounded,
            0,
            0,
            false,
            false,
            config,
            currentLevelFounded,
            currentAvatarIdsFounded,
            finishVipTime

        ));
      }

    }
//////////////make new match//////////

    if (foundedWallet.getGem() < match.getGemCost() || foundedWallet.getGold() < match
        .getGoldCost()) {
      throw new WalletLackException();
    }

    WalletChange wallet = userFeignService.walletChange(
        apiKeyWalletChange,
        new WalletChangeModel(
            -(match.getGemCost()),
            -(match.getGoldCost()),
            Short.parseShort("0"),
            0,
            new ArrayList<>(),
            0,
            false,
            0
        ),
        match.getGamePackageName(),
        userUuId,
        match.getEnv(),
        match.getMarketName()
    );
    PlayerMatch join = new PlayerMatch(
        match.getId(),
        playerDatas,
        userUuIds,
        match.getMaxPlayerInMatch(),
        false
    );
    userFeignService.updateUserStatistic(
        userUuId,
        match.getGamePackageName(),
        match.getEnv(),
        match.getMarketName(),
        0,
        1,
        0,
        match.getGemCost(),
        0,
        0
    );
    return PlayerMatchMapper.playerMatchData(playerMatchRepository.save(join),
        match.getConfig(),
        wallet);
  }

  @Override
  public PlayerMatchData update(
      String id,
      List<PlayerData> players,
      Boolean active) {
    PlayerMatch update = playerMatchRepository.update(
        id,
        players,
        active
    );

    return PlayerMatchMapper.playerMatchData(update, null, null);
  }

  @Override
  public PlayerMatchData load(
      String id,
      String playerUuId) {
    PlayerMatch playerMatch = playerMatchRepository.findById(id);

    if (playerMatch == null) {
      throw new NotFoundException();
    }

    if (!playerMatch.getPlayerUuIds().contains(playerUuId)) {
      throw new PlayerMatchAccessDenied();
    }
    MatchData matchData = matchService.findById(playerMatch.getMatchId());
    return PlayerMatchMapper.playerMatchData(playerMatch, matchData.getConfig(), null);

  }

  @Override
  public PlayerMatchData finish(
      String id,
      String userUuId,
      Boolean win,
      int currentLevel,
      List<Integer> currentAvatarIds,
      long endTimeVip) {
    WalletChange wallet = null;
    CurrentLevelInfo currentLevelInfo = null;
    PlayerMatch playerMatch = playerMatchRepository.finish(id);
    MatchData match = matchService.findById(playerMatch.getMatchId());

    boolean isVip = userHamiFeginService.checkVip(
        userUuId,
        match.getGamePackageName(),
        match.getEnv(),
        match.getMarketName()
    );
    if (win) {
      if (isVip) {
        wallet = userFeignService.walletChange(
            apiKeyWalletChange,
            new WalletChangeModel(
                ((int) (match.getGemWin() * match.getWinnerXpVipCoefficient())) + match
                    .getGemBonus(),
                ((int) (match.getGoldWin() * match.getWinnerXpVipCoefficient())) + match
                    .getGoldBonus(),
                Short.parseShort("0"),
                ((int) (match.getWinXpWinner() * match.getWinnerXpVipCoefficient())) + match
                    .getXpBonus(),
                currentAvatarIds,
                currentLevel,
                false,
                endTimeVip),
            match.getGamePackageName(),
            userUuId,
            match.getEnv(),
            match.getMarketName()
        );
        currentLevelInfo = userFeignService.updateLevel(
            match.getGamePackageName(),
            userUuId,
            match.getEnv(),
            match.getMarketName()
        );
      } else {
        wallet = userFeignService.walletChange(
            apiKeyWalletChange,
            new WalletChangeModel(
                match.getGemWin() + match.getGemBonus(),
                match.getGoldWin() + match.getGoldBonus(),
                Short.parseShort("0"),
                match.getWinXpWinner() + match.getXpBonus(),
                currentAvatarIds,
                currentLevel,
                false,
                endTimeVip),
            match.getGamePackageName(),
            userUuId,
            match.getEnv(),
            match.getMarketName()
        );
        currentLevelInfo = userFeignService.updateLevel(
            match.getGamePackageName(),
            userUuId,
            match.getEnv(),
            match.getMarketName()
        );
      }

      userFeignService.updateUserStatistic(
          userUuId,
          match.getGamePackageName(),
          match.getEnv(),
          match.getMarketName(),
          0,
          0,
          0,
          0,
          1,
          0
      );

    } else {
      if (isVip) {
        wallet = userFeignService.walletChange(
            apiKeyWalletChange,
            new WalletChangeModel(
                0,
                0,
                Short.parseShort("0"),
                ((int) (match.getWinXpLooser() * match.getLooserXpVipCoefficient())) + match
                    .getXpBonus(),
                currentAvatarIds,
                currentLevel,
                false,
                endTimeVip),
            match.getGamePackageName(),
            userUuId,
            match.getEnv(),
            match.getMarketName());
        currentLevelInfo = userFeignService.updateLevel(
            match.getGamePackageName(),
            userUuId,
            match.getEnv(),
            match.getMarketName()
        );
      } else {
        wallet = userFeignService.walletChange(apiKeyWalletChange,
            new WalletChangeModel(
                0,
                0,
                Short.parseShort("0"),
                match.getWinXpLooser() + match.getXpBonus(),
                currentAvatarIds,
                currentLevel,
                false,
                endTimeVip),
            match.getGamePackageName(),
            userUuId,
            match.getEnv(),
            match.getMarketName()
        );
        currentLevelInfo = userFeignService.updateLevel(
            match.getGamePackageName(),
            userUuId,
            match.getEnv(),
            match.getMarketName()
        );
      }

      ////////////send notification if this user achieved level limitation referral/////////////
//TODO : anjam nashod!!!!!!!
//      currentLevelInfo.getLevel();
//
//      ReferralMinLevelInfo minLevelReferral = referralFeignService.getMinLevel(
//          match.getGamePackageName(),
//          match.getEnv(),
//          match.getMarketName()
//      );
//
//      if (currentLevelInfo.getLevel() >= minLevelReferral.getMinLevelForInvitedToConsumePrize()) {
//
//      }

      userFeignService.updateUserStatistic(
          userUuId,
          match.getGamePackageName(),
          match.getEnv(),
          match.getMarketName(),
          0,
          0,
          0,
          0,
          0,
          1
      );

    }

    return PlayerMatchMapper.playerMatchData(playerMatch, match.getConfig(), wallet,
        currentLevelInfo.getLevel());
  }

  //////////////////////////////////////////////////////////////
  ////////////////////////private methods///////////////////////
  ////////////////////////////////////////////////////////////

  private PlayerDataMatch toDataMatch(Object o) {

    String userUuIdFounded = ((LinkedHashMap) o).get("uuid").toString();
    int currentLevelFounded = Integer
        .parseInt(((LinkedHashMap) o).get("currentLevel").toString());
    List<Integer> currentAvatarIdsFounded = new ArrayList<>();
    Object[] objects = ((ArrayList) ((LinkedHashMap) o).get("currentAvatarIds")).toArray();
    for (Object object : objects) {
      currentAvatarIdsFounded.add(Integer.parseInt(object.toString()));
    }
    String usernameFounded = "";
    if (((LinkedHashMap) o).get("username") != null) {
      usernameFounded = ((LinkedHashMap) o).get("username").toString();
    }

    return new PlayerDataMatch(
        userUuIdFounded,
        usernameFounded,
        currentLevelFounded,
        currentAvatarIdsFounded
    );
  }


}
