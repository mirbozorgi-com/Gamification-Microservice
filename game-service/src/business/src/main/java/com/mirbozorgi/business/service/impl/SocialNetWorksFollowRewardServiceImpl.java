package com.mirbozorgi.business.service.impl;

import com.mirbozorgi.business.domain.SocialNetWorksFollowRewardInfo;
import com.mirbozorgi.business.mapper.SocialNetWorksFollowRewardMapper;
import com.mirbozorgi.business.service.SocialNetWorksFollowRewardService;
import com.mirbozorgi.core.document.SocialNetWorksFollowReward;
import com.mirbozorgi.core.domain.SocialNetWorkFollowData;
import com.mirbozorgi.core.repository.docuemnt.SocialNetWorksFollowRewardRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import mirbozorgi.base.domain.user.WalletChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SocialNetWorksFollowRewardServiceImpl implements SocialNetWorksFollowRewardService {


  @Autowired
  private SocialNetWorksFollowRewardRepository socialNetWorksFollowRewardRepository;

  @Override
  public SocialNetWorksFollowRewardInfo update(
      String url,
      int gem,
      int gold,
      Short level,
      int xp,
      boolean hamiAdded,
      long addedVipPeriodTime,
      List<Integer> avatarIds,
      String nameOfSocialNet,
      int gameId,
      String gamePackageName,
      String env) {

    SocialNetWorksFollowReward socialNetWorksFollowReward = socialNetWorksFollowRewardRepository
        .update(
            url,
            new WalletChange(
                gem,
                gold,
                level,
                xp,
                hamiAdded,
                avatarIds,
                addedVipPeriodTime),
            nameOfSocialNet,
            gameId,
            gamePackageName,
            env
        );

    return SocialNetWorksFollowRewardMapper.toInfo(socialNetWorksFollowReward);
  }

  @Override
  public Map<String, SocialNetWorkFollowData> getAll(
      String gamePackageName,
      String env) {
    return socialNetWorksFollowRewardRepository.getAll(gamePackageName, env);
  }

  @Override
  public List<String> getAllNetworks(
      String gamePackageName,
      String env) {
    Map<String, SocialNetWorkFollowData> all = socialNetWorksFollowRewardRepository
        .getAll(gamePackageName, env);
    return new ArrayList<>(all.keySet());
  }


}
