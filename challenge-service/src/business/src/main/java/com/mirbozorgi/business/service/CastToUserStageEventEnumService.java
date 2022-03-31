package com.mirbozorgi.business.service;

import com.mirbozorgi.core.constant.ClientUserEventStage;
import com.mirbozorgi.core.constant.UserEventStage;
import java.util.List;

public interface CastToUserStageEventEnumService {

  List<UserEventStage> makeUserEventStageList(List<ClientUserEventStage> userEventStagesList);

}
