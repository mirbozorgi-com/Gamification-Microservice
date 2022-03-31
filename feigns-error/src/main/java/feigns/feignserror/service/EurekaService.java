package feigns.feignserror.service;

import feigns.feignserror.domain.ServiceInstanceResponse;
import java.util.List;

public interface EurekaService {

  List<ServiceInstanceResponse> findAll();

}
