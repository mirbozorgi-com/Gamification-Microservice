package com.mirbozorgi.api.model;

import com.mirbozorgi.core.domain.LeaderBoardType;
import java.util.List;
import mirbozorgi.base.domain.user.WalletChange;

public class UpdateLeaderBoardReward {

  private LeaderBoardType type;

  private List<WalletChange> walletChanges;

  public LeaderBoardType getType() {
    return type;
  }

  public List<WalletChange> getWalletChanges() {
    return walletChanges;
  }
}
