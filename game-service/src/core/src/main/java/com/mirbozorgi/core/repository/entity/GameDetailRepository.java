package com.mirbozorgi.core.repository.entity;


import com.mirbozorgi.core.entity.GameDetail;

public interface GameDetailRepository {

  GameDetail findById(int id);

  GameDetail findByPublicKey(String publicKey);
}
