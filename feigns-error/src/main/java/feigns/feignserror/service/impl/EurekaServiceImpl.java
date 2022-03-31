package feigns.feignserror.service.impl;

import feigns.feignserror.domain.ServiceInstanceResponse;
import feigns.feignserror.service.EurekaService;
import feigns.feignserror.service.FeignMaintainService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient.EurekaServiceInstance;
import org.springframework.stereotype.Service;

@Service
public class EurekaServiceImpl implements EurekaService {


  @Autowired
  private DiscoveryClient discoveryClient;

  @Autowired
  private FeignMaintainService feignMaintainService;

  @Override
  public List<ServiceInstanceResponse> findAll() {
    List<ServiceInstanceResponse> instanceResponses = new ArrayList<>();
    List<String> services = this.discoveryClient.getServices();
    services.forEach(serviceName -> {
      this.discoveryClient.getInstances(serviceName).forEach(instance -> {
        instanceResponses.add(new ServiceInstanceResponse(
            instance.getServiceId(),
            instance.getInstanceId(),
            instance.getHost(),
            instance.getPort(),
            instance.isSecure(),
            instance.getUri(),
            ((EurekaServiceInstance) instance).getInstanceInfo().getStatus().toString()
        ));

        if (!((EurekaServiceInstance) instance).getInstanceInfo().getStatus().toString()
            .equals("UP")) {
          feignMaintainService.restart(instance.getHost(), instance.getPort());
        }

      });
    });

    return instanceResponses;
  }
}
