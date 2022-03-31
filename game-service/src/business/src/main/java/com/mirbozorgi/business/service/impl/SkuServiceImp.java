package com.mirbozorgi.business.service.impl;

import com.mirbozorgi.business.domain.SkuInfo;
import com.mirbozorgi.business.exception.NotFoundException;
import com.mirbozorgi.business.exception.SkuNotFoundException;
import com.mirbozorgi.business.mapper.SkuMapper;
import com.mirbozorgi.business.service.SkuService;
import com.mirbozorgi.core.entity.Game;
import com.mirbozorgi.core.entity.SKU;
import com.mirbozorgi.core.repository.entity.GameRepository;
import com.mirbozorgi.core.repository.entity.SkuRepository;
import com.mirbozorgi.core.utilities.CurrencyType;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SkuServiceImp implements SkuService {

  @Autowired
  private SkuRepository skuRepository;

  @Autowired
  private GameRepository gameRepository;

  @Override
  public SkuInfo get(int id) {
    return SkuMapper.toSkuData(skuRepository.findById(id));
  }

  @Override
  public SkuInfo getByNameAndGameId(int gameId, String name) {

    SKU sku = skuRepository.findByNameAndGameId(name, gameId);
    if (sku == null) {
      throw new SkuNotFoundException();
    }

    return SkuMapper.toSkuData(sku);
  }

  @Transactional
  @Override
  public SkuInfo update(
      int id,
      int gameId,
      String name,
      String description,
      BigDecimal amount,
      CurrencyType currencyType,
      Boolean active,
      boolean consumable) {
    SKU sku = skuRepository.findById(id);
    if (sku == null) {
      throw new NotFoundException();
    }
    Game game = gameRepository.findById(gameId);
    if (game == null) {
      throw new NotFoundException();
    }
    if (name != null) {
      sku.setName(name);
    }
    if (description != null) {
      sku.setDescription(description);
    }
    if (currencyType != null) {
      sku.setCurrencyType(currencyType);
    }
    if (amount != null) {
      sku.setAmount(amount);
    }
    sku.setActive(active);
    sku.setConsumable(consumable);
    return SkuMapper.toSkuData(skuRepository.save(sku));
  }

  @Transactional
  @Override
  public SkuInfo save(
      int gameId,
      String name,
      String description,
      BigDecimal amount,
      CurrencyType currencyType,
      Boolean active,
      boolean consumable) {
    Game game = gameRepository.findById(gameId);
    if (game == null) {
      throw new NotFoundException();
    }
    SKU sku = new SKU(
        game,
        name,
        description,
        amount,
        currencyType,
        active,
        consumable);
    return SkuMapper.toSkuData(skuRepository.save(sku));
  }

  @Override
  public List<SkuInfo> getAll(Integer gameId) {
    List<SkuInfo> result = new ArrayList<>();
    for (SKU sku : skuRepository.findAll(gameId)) {
      result.add(SkuMapper.toSkuData(sku));
    }
    return result;
  }
}
