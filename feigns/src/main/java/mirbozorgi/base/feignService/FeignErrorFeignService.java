package mirbozorgi.base.feignService;

public interface FeignErrorFeignService {

  int add(String feignServiceName, String methodName);

  void refresh(String host, int port);

  void restart(String host, int port);

  void shutdown(String host, int port);

}
