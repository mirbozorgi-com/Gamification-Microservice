package feigns.feignserror.service.impl;

import feigns.feignserror.entity.MicroServiceRegistry;
import feigns.feignserror.repository.MicroServiceRegistryRepository;
import feigns.feignserror.service.MicroServiceRegistryService;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class MicroServiceRegistryServiceImpl implements MicroServiceRegistryService {

  @Autowired
  private MicroServiceRegistryRepository serviceRegistryRepository;

  @Override
  public MicroServiceRegistry add(String name, int level) {
    return serviceRegistryRepository.add(new MicroServiceRegistry(name, level));
  }

  @Override
  public List<String> findAll() {
    List<String> names = new ArrayList<>();
    List<MicroServiceRegistry> all = serviceRegistryRepository.findAll();
    if (all == null) {
      return new ArrayList<>();
    }
    for (MicroServiceRegistry microServiceRegistry : all) {
      names.add(microServiceRegistry.getName());
    }

    return names;
  }

  @Override
  public MicroServiceRegistry findByName(String name) {
    return serviceRegistryRepository.findByName(name);
  }
}
