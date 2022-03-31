package feigns.feignserror.repository;

import feigns.feignserror.entity.MicroServiceRegistry;
import java.util.List;

public interface MicroServiceRegistryRepository {

  MicroServiceRegistry add(MicroServiceRegistry microServiceRegistry);

  List<MicroServiceRegistry> findAll();

  MicroServiceRegistry findByName(String name);

}
