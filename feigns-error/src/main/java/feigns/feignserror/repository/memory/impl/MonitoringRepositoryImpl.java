package feigns.feignserror.repository.memory.impl;

import feigns.feignserror.repository.memory.MonitoringRepository;
import feigns.feignserror.repository.memory.base.BaseInMemoryRepository;
import feigns.feignserror.repository.memory.base.MemoryRepository;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MonitoringRepositoryImpl implements BaseInMemoryRepository, MonitoringRepository {

  @Autowired
  MemoryRepository redisRepository;

  @Override
  public long incException(String market) {
    return redisRepository.dictionaryIncrement(getKey(), market, 1);
  }

  @Override
  public Map<String, String> get() {
    return redisRepository.dictionaryGet(getKey());
  }

  @Override
  public String getKey() {
    return "MONITORING";
  }

  @Override
  public boolean check() {
    return redisRepository.check();
  }
}
