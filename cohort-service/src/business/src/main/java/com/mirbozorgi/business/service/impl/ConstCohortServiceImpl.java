package com.mirbozorgi.business.service.impl;

import com.mirbozorgi.business.exception.NotFoundException;
import com.mirbozorgi.business.service.ConstCohortService;
import com.mirbozorgi.business.domain.ConstCohortData;
import com.mirbozorgi.business.mapper.ConstCohortMapper;
import com.mirbozorgi.core.entity.ConstCohort;
import com.mirbozorgi.core.repository.document.ConstCohortRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConstCohortServiceImpl implements ConstCohortService {

  @Autowired
  private ConstCohortRepository constCohortRepository;

  @Override
  public ConstCohortData save(String gamePackageName, String name, Object config) {
    ConstCohort constCohort = constCohortRepository.save(new ConstCohort(
        gamePackageName,
        name,
        config
    ));

    return ConstCohortMapper.toConstCohortData(
        constCohort.getStringId(),
        constCohort.getGamePackageName(),
        constCohort.getName(),
        constCohort.getConfig()
    );
  }

  @Override
  public void delete(String id) {
    ConstCohort constCohort = constCohortRepository.findById(id);
    if (constCohort == null) {
      throw new NotFoundException();
    }
    constCohortRepository.delete(constCohort);

  }

  @Override
  public ConstCohortData update(String id, String gamePackageName, String name, Object config) {
    ConstCohort constCohort = constCohortRepository.update(
        id,
        gamePackageName,
        name,
        config
    );

    return ConstCohortMapper.toConstCohortData(
        constCohort.getStringId(),
        constCohort.getGamePackageName(),
        constCohort.getName(),
        constCohort.getConfig()
    );

  }

  @Override
  public ConstCohortData findById(String id) {
    ConstCohort constCohort = constCohortRepository.findById(id);
    if (constCohort == null) {
      throw new NotFoundException();
    }
    return ConstCohortMapper.toConstCohortData(
        constCohort.getStringId(),
        constCohort.getGamePackageName(),
        constCohort.getName(),
        constCohort.getConfig()
    );
  }

  @Override
  public ConstCohortData findByName(String name) {
    ConstCohort constCohort = constCohortRepository.findByName(name);
    return ConstCohortMapper.toConstCohortData(
        constCohort.getStringId(),
        constCohort.getGamePackageName(),
        constCohort.getName(),
        constCohort.getConfig()
    );
  }

  @Override
  public List<ConstCohortData> findAll() {
    List<ConstCohortData> constCohortDatas = new ArrayList<>();

    for (ConstCohort constCohort : constCohortRepository.findAll()) {
      constCohortDatas.add(ConstCohortMapper.toConstCohortData(
          constCohort.getStringId(),
          constCohort.getGamePackageName(),
          constCohort.getName(),
          constCohort.getConfig()
      ));
    }

    return constCohortDatas;
  }
}
