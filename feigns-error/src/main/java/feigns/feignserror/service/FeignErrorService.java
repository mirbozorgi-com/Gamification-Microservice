package feigns.feignserror.service;

import feigns.feignserror.domain.ErrorFeignResponse;

public interface FeignErrorService {

  ErrorFeignResponse incError(String feignName, String feignMethod);


}
