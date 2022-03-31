package com.mirbozorgi.business.service.impl;

import com.mirbozorgi.business.service.CastToUserStageEventEnumService;
import com.mirbozorgi.core.constant.ClientUserEventStage;
import com.mirbozorgi.core.constant.UserEventStage;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CastToUserStageEventEnumServiceServiceImpl implements CastToUserStageEventEnumService {

  @Override
  public List<UserEventStage> makeUserEventStageList(
      List<ClientUserEventStage> userEventStagesList) {
    List<UserEventStage> newUserEventStagesList = new ArrayList<>();
    for (ClientUserEventStage clientUserEventStage : userEventStagesList) {
      if (clientUserEventStage.toString().equals(UserEventStage.FINISHED.toString())) {
        newUserEventStagesList.add(UserEventStage.FINISHED);
      } else if (clientUserEventStage.toString().equals(UserEventStage.CLAIMED.toString())) {
        newUserEventStagesList.add(UserEventStage.CLAIMED);
      } else if (clientUserEventStage.toString().equals(UserEventStage.ACTIVE.toString())) {
        newUserEventStagesList.add(UserEventStage.ACTIVE);
      } else if (clientUserEventStage.toString().equals(UserEventStage.PRE_ACTIVE.toString())) {
        newUserEventStagesList.add(UserEventStage.PRE_ACTIVE);
      } else if (clientUserEventStage.toString().equals(UserEventStage.NOT_START.toString())) {
        newUserEventStagesList.add(UserEventStage.NOT_START);
      } else if (clientUserEventStage.toString().equals(UserEventStage.JOINABLE.toString())) {
        newUserEventStagesList.add(UserEventStage.JOINABLE);
      }
      else if (clientUserEventStage.toString().equals(UserEventStage.TERMINATED.toString())) {
        newUserEventStagesList.add(UserEventStage.TERMINATED);
      }
    }
    return newUserEventStagesList;
  }

}
