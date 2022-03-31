package mirbozorgi.base.domain.notification;

import java.util.List;

public class FCMGameMessageInfoGetAll {

  private List<FCMGameMessageInfo> fcmGameMessageInfos;

  public FCMGameMessageInfoGetAll() {
  }

  public FCMGameMessageInfoGetAll(
      List<FCMGameMessageInfo> fcmGameMessageInfos) {
    this.fcmGameMessageInfos = fcmGameMessageInfos;
  }

  public List<FCMGameMessageInfo> getFcmGameMessageInfos() {
    return fcmGameMessageInfos;
  }
}
