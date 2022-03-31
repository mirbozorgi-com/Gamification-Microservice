package feigns.feignserror.repository;

import java.time.LocalDateTime;

public interface FeignErrorRepository {

  int incError(String feignName, LocalDateTime lastError, int maxError);

}
