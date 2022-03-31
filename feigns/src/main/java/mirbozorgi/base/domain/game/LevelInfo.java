package mirbozorgi.base.domain.game;

public class LevelInfo {

  private int id;
  private String levelName;
  private int gameId;
  private String gameName;
  private int minXp;
  private int maxXp;
  private int levelNumber;

  public LevelInfo() {
  }

  public LevelInfo(int id, String levelName, int gameId, String gameName,
      int minXp, int maxXp, int levelNumber) {
    this.id = id;
    this.levelName = levelName;
    this.gameId = gameId;
    this.gameName = gameName;
    this.minXp = minXp;
    this.maxXp = maxXp;
    this.levelNumber = levelNumber;
  }

  public int getLevelNumber() {
    return levelNumber;
  }

  public int getId() {
    return id;
  }

  public String getLevelName() {
    return levelName;
  }

  public int getGameId() {
    return gameId;
  }

  public String getGameName() {
    return gameName;
  }

  public int getMinXp() {
    return minXp;
  }

  public int getMaxXp() {
    return maxXp;
  }
}
