package mirbozorgi.base.feigns;

import mirbozorgi.base.domain.leaderboard.LeaderBoardAddModel;
import mirbozorgi.base.domain.leaderboard.UpdateGemBuyModel;
import mirbozorgi.base.domain.leaderboard.UpdateReferalModel;
import mirbozorgi.base.domain.leaderboard.UpdateXpModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient("leaderboard-service")
public interface LeaderBoardFeign {


  @RequestMapping(method = RequestMethod.GET, path = "leader-board/inc-score")
  Object incScore(
      @RequestHeader String gamePackageName,
      @RequestHeader String marketName,
      @RequestHeader String env,
      @RequestParam String challengeId,
      @RequestHeader String uuid,
      @RequestParam int score);

  @RequestMapping(method = RequestMethod.POST, path = "leader-board/add")
  Object add(@Validated @RequestBody LeaderBoardAddModel model,
      @RequestHeader String gamePackageName,
      @RequestHeader String uuid,
      @RequestHeader String marketName,
      @RequestHeader String env);


  @RequestMapping(value = "leader-board-gem-buy/update", method = RequestMethod.POST)
  Object updateGem(
      @Validated @RequestBody UpdateGemBuyModel model,
      @RequestHeader String gamePackageName,
      @RequestHeader String uuid,
      @RequestHeader String env,
      @RequestHeader String marketName);


  @RequestMapping(value = "leader-board-refral/update", method = RequestMethod.POST)
  Object updateReferal(
      @Validated @RequestBody UpdateReferalModel model,
      @RequestHeader String gamePackageName,
      @RequestHeader String uuid,
      @RequestHeader String env,
      @RequestHeader String marketName);


  @RequestMapping(value = "leader-board-xp/update", method = RequestMethod.POST)
  Object updateXp(
      @Validated @RequestBody UpdateXpModel model,
      @RequestHeader String gamePackageName,
      @RequestHeader String uuid,
      @RequestHeader String env,
      @RequestHeader String marketName);

}
