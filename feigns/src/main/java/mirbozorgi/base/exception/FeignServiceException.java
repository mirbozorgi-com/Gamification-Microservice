package mirbozorgi.base.exception;

import org.springframework.http.HttpStatus;

public class FeignServiceException extends CustomException {

  public FeignServiceException(String methodName, String feignService) {
    super("feign service : " + feignService + " is not available", HttpStatus.SERVICE_UNAVAILABLE);
    this.setData("method name is : " + methodName);
  }

}
