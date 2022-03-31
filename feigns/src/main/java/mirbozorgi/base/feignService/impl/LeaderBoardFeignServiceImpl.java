package mirbozorgi.base.feignService.impl;

import java.util.List;
import mirbozorgi.base.domain.leaderboard.LeaderBoardAddModel;
import mirbozorgi.base.domain.leaderboard.UpdateGemBuyModel;
import mirbozorgi.base.domain.leaderboard.UpdateReferalModel;
import mirbozorgi.base.domain.leaderboard.UpdateXpModel;
import mirbozorgi.base.exception.GlobalExceptionService;
import mirbozorgi.base.feignService.FeignErrorFeignService;
import mirbozorgi.base.feignService.LeaderBoardFeignService;
import mirbozorgi.base.feigns.LeaderBoardFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeaderBoardFeignServiceImpl implements LeaderBoardFeignService {

  @Autowired
  private LeaderBoardFeign leaderBoardFeign;

  @Autowired
  private FeignErrorFeignService feignErrorFeign;

  @Override
  public Object incScore(
      String gamePackageName,
      String marketName,
      String env,
      String challengeId,
      String userUuId,
      int score) {
    Object o = null;
    try {

      o = leaderBoardFeign.incScore(
          gamePackageName,
          marketName,
          env,
          challengeId,
          userUuId,
          score
      );


    } catch (RuntimeException ex) {

      GlobalExceptionService.toGlobalException(
          "incScore", "leaderboard-service", ex, feignErrorFeign
      );


    }

    return o;
  }

  @Override
  public Object add(
      LeaderBoardAddModel model,
      String gamePackageName,
      String uuid,
      String marketName,
      String env) {
    Object o = null;
    try {

      o = leaderBoardFeign.add(
          model,
          gamePackageName,
          uuid,
          marketName,
          env);


    } catch (RuntimeException ex) {

      GlobalExceptionService.toGlobalException(
          "add", "leaderboard-service", ex, feignErrorFeign
      );
    }

    return o;
  }

  @Override
  public void updateGem(
      String uuid,
      int gem,
      String username,
      String gamePackageName,
      String env,
      String marketName,
      int level,
      List<Integer> avatarIds,
      long endVipTime
  ) {
    try {

      leaderBoardFeign.updateGem(
          new UpdateGemBuyModel(
              gem,
              level,
              username,
              avatarIds,
              endVipTime
          ),
          gamePackageName,
          uuid,
          env,
          marketName);


    } catch (RuntimeException ex) {

      GlobalExceptionService.toGlobalException(
          "updateGerm", "leaderboard-service", ex, feignErrorFeign
      );
    }

  }

  @Override
  public void updateReferal(
      String uuid,
      int quantity,
      String username,
      String gamePackageName,
      String env,
      String marketName,
      int level,
      List<Integer> avatarIds,
      long endVipTime) {
    try {

      leaderBoardFeign.updateReferal(
          new UpdateReferalModel(
              quantity,
              level,
              username,
              avatarIds,
              endVipTime
          ),
          gamePackageName,
          uuid,
          marketName,
          env);


    } catch (RuntimeException ex) {

      GlobalExceptionService.toGlobalException(
          "updateReferal", "leaderboard-service", ex, feignErrorFeign
      );
    }
  }

  @Override
  public void updateXp(
      String uuid,
      int xp,
      String username,
      String gamePackageName,
      String env,
      String marketName,
      int level,
      List<Integer> avatarIds,
      long endVipTime,
      int xpGlobal) {
    try {

      leaderBoardFeign.updateXp(
          new UpdateXpModel(
              xp,
              level,
              username,
              avatarIds,
              endVipTime,
              xpGlobal
          ),
          gamePackageName,
          uuid,
          env,
          marketName
      );


    } catch (RuntimeException ex) {

      GlobalExceptionService.toGlobalException(
          "updateXp", "leaderboard-service", ex, feignErrorFeign
      );
    }
  }
}
