package mirbozorgi.base.exception;

import com.netflix.client.ClientException;
import feign.FeignException;
import mirbozorgi.base.feignService.FeignErrorFeignService;
import org.apache.http.conn.HttpHostConnectException;


public class GlobalExceptionService {


  public static void toGlobalException(String methodName, String feignService,
      RuntimeException ex, FeignErrorFeignService feignErrorFeign) {

    if (ex.getClass().equals(FeignException.class)) {
      if (ex.getCause() == null && ((FeignException) ex).status() == 404) {
        throw new NotFoundFeignEntityException(methodName, feignService);

      }
    }

    if (ex.getCause().getClass().equals(HttpHostConnectException.class) ||
        ex.getCause().getClass().equals(ClientException.class)) {

      int error = feignErrorFeign.add(
          feignService,
          methodName);
//      if (error == 0) {
//
//        feignErrorFeign.restart();
//      }

      throw new FeignServiceException(methodName, feignService);
    }

    throw ex;

  }

}
