package feigns.feignserror.service;

import feigns.feignserror.entity.MicroServiceRegistry;
import java.util.List;

public interface MicroServiceRegistryService {

  MicroServiceRegistry add(String name, int level);

  List<String> findAll();

  MicroServiceRegistry findByName(String name);

}
