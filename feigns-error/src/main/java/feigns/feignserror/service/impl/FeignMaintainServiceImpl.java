package feigns.feignserror.service.impl;

import feigns.feignserror.domain.CheckAvailibilityResponse;
import feigns.feignserror.domain.ServiceInstanceResponse;
import feigns.feignserror.service.EurekaService;
import feigns.feignserror.service.FeignMaintainService;
import feigns.feignserror.service.HttpClientService;
import feigns.feignserror.service.MicroServiceRegistryService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

@Service
public class FeignMaintainServiceImpl implements FeignMaintainService {

  @Autowired
  private HttpClientService httpClientService;

  @Autowired
  private EurekaService eurekaService;

  @Autowired
  private MicroServiceRegistryService microServiceRegistryService;

  @Override
  public void refresh(String host, int port) {

    String url = "http://" + host + ":" + port + "/actuator/refresh";

    httpClientService.request(HttpMethod.POST,
        url,
        null,
        null,
        null,
        Object.class);


  }

  @Override
  public void shutDown(String host, int port) {
    String url = "http://" + host + ":" + port + "/actuator/shutdown";

    httpClientService.request(HttpMethod.POST,
        url,
        null,
        null,
        null,
        Object.class);

  }

  @Override
  public void restart(String host, int port) {

    String url = "http://" + host + ":" + port + "/actuator/restart";

    httpClientService.request(HttpMethod.POST,
        url,
        null,
        null,
        null,
        Object.class);

  }

  @Override
  public CheckAvailibilityResponse checkAvailability() {
    CheckAvailibilityResponse checkAvailibilityResponse = new CheckAvailibilityResponse();

    List<String> servicesName = microServiceRegistryService.findAll();

    List<String> serviceInEurekaNames = new ArrayList<>();
    List<ServiceInstanceResponse> serviceRegisteredInEureka = eurekaService.findAll();

    for (ServiceInstanceResponse serviceInstanceResponse : serviceRegisteredInEureka) {
      serviceInEurekaNames.add(serviceInstanceResponse.getServiceId());
    }

    for (String s : servicesName) {

      if (serviceInEurekaNames.contains(s)) {
        checkAvailibilityResponse.getUpServices().add(s);
      } else {
        checkAvailibilityResponse.getDownServices().add(s);
      }
    }
    return checkAvailibilityResponse;
  }
}
