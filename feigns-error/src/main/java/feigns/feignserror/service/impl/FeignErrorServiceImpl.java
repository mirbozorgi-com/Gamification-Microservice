package feigns.feignserror.service.impl;

import feigns.feignserror.domain.ErrorFeignResponse;
import feigns.feignserror.domain.ErrorForFeigns;
import feigns.feignserror.repository.FeignErrorRepository;
import feigns.feignserror.service.FeignErrorService;
import java.time.LocalDateTime;
import mirbozorgi.base.feignService.TimeFeignService;
import mirbozorgi.base.service.CustomLoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FeignErrorServiceImpl implements FeignErrorService {

  @Autowired
  private FeignErrorRepository repository;

  @Autowired
  private CustomLoggerService customLoggerService;

  @Autowired
  private TimeFeignService timeFeignService;


  @Value("${max.feign-error.time}")
  private int maxFignError;

  @Transactional
  @Override
  public ErrorFeignResponse incError(String feignName, String feignMethod) {
    LocalDateTime lastError = timeFeignService.now();

    int error = repository.incError(feignName, lastError, maxFignError);

    customLoggerService.putWithOutLog(feignName,
        new ErrorForFeigns(lastError, "number of error: " + error));

    customLoggerService
        .put(feignName, "error in " + feignMethod);

    return new ErrorFeignResponse(error);
  }
}
