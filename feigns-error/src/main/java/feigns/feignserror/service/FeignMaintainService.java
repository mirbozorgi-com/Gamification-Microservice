package feigns.feignserror.service;

import feigns.feignserror.domain.CheckAvailibilityResponse;

public interface FeignMaintainService {

  void refresh(String host, int port);

  void shutDown(String host, int port);

  void restart(String host, int port);

  CheckAvailibilityResponse checkAvailability();

}
