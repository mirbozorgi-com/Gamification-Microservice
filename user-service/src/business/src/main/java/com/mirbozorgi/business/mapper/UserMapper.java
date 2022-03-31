package com.mirbozorgi.business.mapper;

import com.mirbozorgi.business.domain.user.UserGetResponse;
import com.mirbozorgi.business.domain.user.UserGetWalletAndStatistic;
import com.mirbozorgi.business.domain.user.UserInfo;
import com.mirbozorgi.business.domain.user.UserStatistic;
import com.mirbozorgi.core.domain.Statistic;
import com.mirbozorgi.core.domain.UserGameProfile;
import com.mirbozorgi.core.domain.Wallet;
import com.mirbozorgi.core.document.User;

public class UserMapper {

  public static UserInfo toUserInfo(User user, String market, String packageName, String env) {
    UserGameProfile userGameProfile = user.getGameProfiles().get(packageName).get(env).get(market);

    return new UserInfo(
        user.getUuid(),
        packageName,
        market,
        env,
        userGameProfile.getCohortName(),
        userGameProfile.isActive(),
        userGameProfile.getUsername(),
        userGameProfile.isTest(),
        userGameProfile.isDebug(),
        userGameProfile.getBanned(),
        userGameProfile.getClientVersion(),
        userGameProfile.getCreationDate(),
        userGameProfile.getPlayerStatus(),
        userGameProfile.isVerified(),
        userGameProfile.getWallet());
  }


  public static UserGetResponse toGetResponse(User user) {

    return new UserGetResponse(user.getUuid());

  }

  public static UserStatistic toGetUserStatistic(Statistic statistic) {

    return new UserStatistic(
        statistic.getWin(),
        statistic.getLose(),
        statistic.getInvitations(),
        statistic.getGamesParticipated(),
        statistic.getHamiCount(),
        statistic.getGemSpent()
    );
  }

  public static UserGetWalletAndStatistic toGetUserWalletAndStatistic(Wallet wallet,
      Statistic statistic) {

    return new UserGetWalletAndStatistic(
        wallet.getGem(),
        wallet.getGold(),
        wallet.getLevel(),
        wallet.getXp(),
        wallet.getActiveAvatarIds(),
        wallet.getPurchasedAvatars(),
        statistic.getWin(),
        statistic.getLose(),
        statistic.getInvitations(),
        statistic.getGamesParticipated(),
        statistic.getHamiCount(),
        statistic.getGemSpent()
    );
  }

}
