package mirbozorgi.base.domain.hami;


public class UserHamiData {

  private String id;

  private String uuid;

  private String username;

  private HamiData hamiData;

  public UserHamiData(String id, String uuid, String username, HamiData hamiData) {
    this.id = id;
    this.uuid = uuid;
    this.username = username;
    this.hamiData = hamiData;
  }

  public UserHamiData() {
  }

  public String getId() {
    return id;
  }

  public String getUuid() {
    return uuid;
  }

  public String getUsername() {
    return username;
  }

  public HamiData getHamiData() {
    return hamiData;
  }
}
