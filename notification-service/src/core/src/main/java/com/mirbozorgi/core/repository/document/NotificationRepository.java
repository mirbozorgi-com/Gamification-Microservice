package com.mirbozorgi.core.repository.document;

import com.mirbozorgi.core.docuemnt.Notification;
import java.util.List;

public interface NotificationRepository {

  Notification save(Notification notification);

  Notification findById(String id);

  List<Notification> findAll(
      String name,
      String gamePackageName,
      String env,
      String marketName);

}
