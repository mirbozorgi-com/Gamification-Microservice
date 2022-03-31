package com.mirbozorgi.business.service.impl;

import com.mirbozorgi.business.service.GameDetailService;
import com.mirbozorgi.business.domain.GameDetailInfo;
import com.mirbozorgi.business.mapper.GameDetailMapper;
import com.mirbozorgi.core.repository.entity.GameDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameDetailServiceImpl implements GameDetailService {

  @Autowired
  private GameDetailRepository gameDetailRepository;


  @Override
  public GameDetailInfo getById(int id) {
    return GameDetailMapper.toGameDetailInfo(gameDetailRepository.findById(id));
  }

  @Override
  public GameDetailInfo getByPublicKey(String publicKey) {
    return GameDetailMapper.toGameDetailInfo(gameDetailRepository.findByPublicKey(publicKey));
  }
}
