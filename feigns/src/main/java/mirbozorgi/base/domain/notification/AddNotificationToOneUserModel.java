package mirbozorgi.base.domain.notification;

import java.util.List;
import mirbozorgi.base.domain.hami.HamiAndNotificationType;

public class AddNotificationToOneUserModel {

  private String name;
  private String config;
  private Boolean removeAble;
  private int gem;
  private int gold;
  private Short level;
  private int xp;
  private boolean hamiAded;
  private List<Integer> avatarIds;
  private long addedVipPeriodTime;
  private String playerUuId;
  private String title;
  private String message;
  private String topic;
  private HamiAndNotificationType type;


  public AddNotificationToOneUserModel() {
  }

  public AddNotificationToOneUserModel(String name, String config, Boolean removeAble, int gem,
      int gold, Short level, int xp, boolean hamiAded, List<Integer> avatarIds,
      long addedVipPeriodTime, String playerUuId, String title, String message,
      String topic,
      HamiAndNotificationType type) {
    this.name = name;
    this.config = config;
    this.removeAble = removeAble;
    this.gem = gem;
    this.gold = gold;
    this.level = level;
    this.xp = xp;
    this.hamiAded = hamiAded;
    this.avatarIds = avatarIds;
    this.addedVipPeriodTime = addedVipPeriodTime;
    this.playerUuId = playerUuId;
    this.title = title;
    this.message = message;
    this.topic = topic;
    this.type = type;
  }

  public HamiAndNotificationType getType() {
    return type;
  }

  public String getTitle() {
    return title;
  }

  public String getMessage() {
    return message;
  }

  public String getTopic() {
    return topic;
  }

  public String getPlayerUuId() {
    return playerUuId;
  }

  public int getGem() {
    return gem;
  }

  public int getGold() {
    return gold;
  }

  public Short getLevel() {
    return level;
  }

  public int getXp() {
    return xp;
  }

  public boolean isHamiAded() {
    return hamiAded;
  }

  public List<Integer> getAvatarIds() {
    return avatarIds;
  }

  public long getAddedVipPeriodTime() {
    return addedVipPeriodTime;
  }

  public Boolean getRemoveAble() {
    return removeAble;
  }


  public String getName() {
    return name;
  }

  public String getConfig() {
    return config;
  }
}
