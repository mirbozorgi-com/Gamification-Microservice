package mirbozorgi.base.feignService.impl;

import mirbozorgi.base.domain.feign.ErrorFeignResponse;
import mirbozorgi.base.feignService.FeignErrorFeignService;
import mirbozorgi.base.feignService.SerializerFeignService;
import mirbozorgi.base.feigns.FeignErrorFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeignErrorFeignServiceImpl implements FeignErrorFeignService {

  @Autowired
  private FeignErrorFeign feignErrorFeign;

  @Autowired
  private SerializerFeignService serializerFeignService;

  @Override
  public int add(String feignServiceName, String methodName) {

    Object o = feignErrorFeign.add(feignServiceName, methodName);
    ErrorFeignResponse errorFeignResponse = serializerFeignService
        .toObjectFromFeign(o, ErrorFeignResponse.class);

    return errorFeignResponse.getError();
  }

  @Override
  public void refresh(String host, int port) {
    feignErrorFeign.refresh(host, port);
  }

  @Override
  public void restart(String host, int port) {
    feignErrorFeign.restart(host, port);

  }

  @Override
  public void shutdown(String host, int port) {
    feignErrorFeign.shutdown(host, port);

  }
}
