package mirbozorgi.base.domain.hami;


public class HamiData {

  private String name;

  private Object config;

  private HamiAndNotificationType hamiType;

  public HamiData(String name, Object config, HamiAndNotificationType hamiType) {
    this.name = name;
    this.config = config;
    this.hamiType = hamiType;
  }

  public HamiAndNotificationType getHamiType() {
    return hamiType;
  }

  public HamiData() {
  }

  public String getName() {
    return name;
  }

  public Object getConfig() {
    return config;
  }
}
