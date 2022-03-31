package com.mirbozorgi.business.mapper;

import com.mirbozorgi.business.domain.NotificationInfo;
import com.mirbozorgi.core.docuemnt.Notification;

public class NotificationMapper {

  public static NotificationInfo toNotificationInfo(Notification notification) {

    return new NotificationInfo(
        notification.getStringId(),
        notification.getName(),
        notification.getGamePackageName(),
        notification.getEnv(),
        notification.getMarketName(),
        notification.getConfig(),
        notification.getCreationDate(),
        notification.getMinClientVersion(),
        notification.getMaxClientVersion(),
        notification.getRemoveAble(),
        notification.getWalletChange(),
        notification.getTitle(),
        notification.getTopic(),
        notification.getMessage(),
        notification.getType(),
        notification.getFcmSend()
    );

  }

}
