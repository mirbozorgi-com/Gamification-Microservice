package mirbozorgi.base.domain.hami;

public class AddModel {

  private String username;
  private String name;
  private String config;
  private HamiAndNotificationType hamiType;

  public AddModel(String username, String name, String config, HamiAndNotificationType hamiType) {
    this.username = username;
    this.name = name;
    this.config = config;
    this.hamiType = hamiType;
  }

  public HamiAndNotificationType getHamiType() {
    return hamiType;
  }

  public String getUsername() {
    return username;
  }

  public String getName() {
    return name;
  }


  public String getConfig() {
    return config;
  }
}
