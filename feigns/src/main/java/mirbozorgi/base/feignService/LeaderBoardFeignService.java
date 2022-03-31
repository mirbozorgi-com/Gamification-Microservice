package mirbozorgi.base.feignService;

import java.util.List;
import mirbozorgi.base.domain.leaderboard.LeaderBoardAddModel;

public interface LeaderBoardFeignService {

  Object incScore(
      String gamePackageName,
      String marketName,
      String env,
      String challengeId,
      String userUuId,
      int score);

  Object add(
      LeaderBoardAddModel model,
      String gamePackageName,
      String uuid,
      String marketName,
      String env);

  void updateGem(String uuid,
      int gem,
      String username,
      String gamePackageName,
      String env,
      String marketName,
      int level,
      List<Integer> avatarIds,
      long endVipTime);

  void updateReferal(
      String uuid,
      int quantity,
      String username,
      String gamePackageName,
      String env,
      String marketName,
      int level,
      List<Integer> avatarIds,
      long endVipTime);

  void updateXp(
      String uuid,
      int xp,
      String username,
      String gamePackageName,
      String env,
      String marketName,
      int level,
      List<Integer> avatarIds,
      long endVipTime,
      int xpGlobal);
}
