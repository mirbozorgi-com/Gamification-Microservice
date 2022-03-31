package feigns.feignserror.repository.impl;

import feigns.feignserror.entity.FeignErrors;
import feigns.feignserror.repository.FeignErrorRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class FeignErrorRepositoryImpl extends CustomRepository implements FeignErrorRepository {


  @Override
  public int incError(String name, LocalDateTime lastError, int maxError) {
    FeignErrors feign = findByName(name);
    if (feign == null) {
      return save(FeignErrors.class, new FeignErrors(
          name,
          1,
          null,
          lastError,
          0
      )).getError();
    }

    if (feign.getError() >= maxError) {
      int error = 0;
      int timeOfRestart = feign.getTimeOfRestart() + 1;
      entityManager.createQuery("update FeignErrors set "
          + " lastError = :lastError, "
          + " lastRestart = :lastError, "
          + " timeOfRestart = :timeOfRestart, "
          + " error = :error "
          + " Where name = :name")
          .setParameter("name", name)
          .setParameter("lastError", lastError)
          .setParameter("timeOfRestart", timeOfRestart)
          .setParameter("error", error)
          .executeUpdate();
      entityManager.refresh(entityManager.getReference(FeignErrors.class, feign.getId()));
      return 0;
    }

    int error = feign.getError() + 1;
    entityManager.createQuery("update FeignErrors set "
        + " lastError = :lastError, "
        + " error = :error "
        + " Where name = :name")
        .setParameter("name", name)
        .setParameter("lastError", lastError)
        .setParameter("error", error)
        .executeUpdate();
    entityManager.refresh(entityManager.getReference(FeignErrors.class, feign.getId()));
    return findByName(name).getError();
  }


  ///////////////////////
  /////private method/////
  ////////////////////////
  private FeignErrors findByName(String name) {

    List<FeignErrors> objs = entityManager
        .createQuery("select o from FeignErrors o where o.name = :name ")
        .setParameter("name", name)
        .getResultList();

    if (!objs.isEmpty()) {
      FeignErrors obj = objs.get(0);
      return obj;
    }
    return null;


  }


}
