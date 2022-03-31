package com.mirbozorgi.business.service;

import com.mirbozorgi.business.domain.GameDetailInfo;

public interface GameDetailService {

  GameDetailInfo getById(int id);

  GameDetailInfo getByPublicKey(String publicKey);

}
