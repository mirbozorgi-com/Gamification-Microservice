package mirbozorgi.base.exception;

import org.springframework.http.HttpStatus;

public class NotFoundFeignEntityException extends CustomException {

  public NotFoundFeignEntityException(String methodName, String feignService) {
    super("feign service : " + feignService + ",entiry or docuement," + " is not found",
        HttpStatus.NOT_FOUND);
    this.setData("method name is : " + methodName);
  }

}
