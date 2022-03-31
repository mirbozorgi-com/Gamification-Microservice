package mirbozorgi.base.domain.leaderboard;

public class LeaderBoardAddModel {


  private String challengeId;
  private String username;
  private Boolean allMarketInclude;

  public LeaderBoardAddModel(
      String challengeId, String username, Boolean allMarketInclude) {

    this.challengeId = challengeId;
    this.username = username;
    this.allMarketInclude = allMarketInclude;
  }

  public Boolean getAllMarketInclude() {
    return allMarketInclude;
  }


  public String getChallengeId() {
    return challengeId;
  }

  public String getUsername() {
    return username;
  }
}
