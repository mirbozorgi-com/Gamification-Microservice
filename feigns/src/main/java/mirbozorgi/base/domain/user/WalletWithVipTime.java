package mirbozorgi.base.domain.user;


public class WalletWithVipTime {

  private Wallet wallet;
  private long endVipTime;

  public WalletWithVipTime() {
  }

  public WalletWithVipTime(Wallet wallet, long endVipTime) {
    this.wallet = wallet;
    this.endVipTime = endVipTime;
  }

  public Wallet getWallet() {
    return wallet;
  }

  public long getEndVipTime() {
    return endVipTime;
  }
}
