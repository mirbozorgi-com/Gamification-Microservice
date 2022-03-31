package feigns.feignserror.repository.impl;

import feigns.feignserror.entity.MicroServiceRegistry;
import feigns.feignserror.repository.MicroServiceRegistryRepository;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class ServiceRegistryRepositoryImpl extends CustomRepository implements
    MicroServiceRegistryRepository {

  @Override
  public MicroServiceRegistry add(MicroServiceRegistry service) {
    return save(MicroServiceRegistry.class, service);
  }

  @Override
  public List<MicroServiceRegistry> findAll() {

    return listQueryWrapper(entityManager.createQuery(
        "select m from MicroServiceRegistry m order by m.id desc ",
        MicroServiceRegistry.class));
  }

  @Override
  public MicroServiceRegistry findByName(String name) {
    return findQueryWrapper(entityManager
        .createQuery("select g from MicroServiceRegistry g where g.name= :name ",
            MicroServiceRegistry.class)
        .setParameter("name", name));
  }
}
