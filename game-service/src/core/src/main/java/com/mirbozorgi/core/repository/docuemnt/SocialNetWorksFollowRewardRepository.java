package com.mirbozorgi.core.repository.docuemnt;

import com.mirbozorgi.core.document.SocialNetWorksFollowReward;
import com.mirbozorgi.core.domain.SocialNetWorkFollowData;
import java.util.Map;
import mirbozorgi.base.domain.user.WalletChange;

public interface SocialNetWorksFollowRewardRepository {

  SocialNetWorksFollowReward update(
      String url,
      WalletChange walletChange,
      String nameOfSocialNet,
      int gameId,
      String gamePackageName,
      String env);

  Map<String, SocialNetWorkFollowData> getAll(
      String gamePackageName,
      String env
  );

}
