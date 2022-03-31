package mirbozorgi.base.domain.user;

import java.util.ArrayList;
import java.util.List;

public class UserByXpGetResponse {

  private String uuid;
  private String username;
  private int currentLevel;
  private List<Integer> currentAvatarIds;

  public UserByXpGetResponse(
      String uuid,
      String username,
      int currentLevel,
      List<Integer> currentAvatarIds) {
    this.uuid = uuid;
    this.username = username;
    this.currentLevel = currentLevel;
    this.currentAvatarIds = currentAvatarIds;
  }

  public UserByXpGetResponse() {
    currentAvatarIds = new ArrayList<>();
  }


  public int getCurrentLevel() {
    return currentLevel;
  }

  public List<Integer> getCurrentAvatarIds() {
    return currentAvatarIds;
  }

  public String getUsername() {
    return username;
  }

  public String getUuid() {
    return uuid;
  }


}
