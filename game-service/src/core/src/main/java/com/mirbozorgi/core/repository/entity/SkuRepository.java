package com.mirbozorgi.core.repository.entity;

import com.mirbozorgi.core.entity.SKU;
import java.util.List;

public interface SkuRepository {

  SKU findById(long id);

  SKU findByNameAndGameId(String name, int gameId);

  SKU save(SKU sku);

  List<SKU> findAll(Integer gameId);
}
