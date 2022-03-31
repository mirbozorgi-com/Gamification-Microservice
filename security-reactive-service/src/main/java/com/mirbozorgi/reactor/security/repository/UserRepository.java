package com.mirbozorgi.reactor.security.repository;

import com.mirbozorgi.reactor.security.entity.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User, Long> {

  Mono<User> findByUuid(String uuid);

  Mono<User> findByEmailAndGamePackageNameAndEnvAndMarket(String email,
      String gamePackageName,
      String env,
      String market);

  Mono<User> findByDeviceId(String deviceId);
}
