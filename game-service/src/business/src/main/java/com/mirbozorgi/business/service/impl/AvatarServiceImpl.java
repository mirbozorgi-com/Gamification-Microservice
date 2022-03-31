package com.mirbozorgi.business.service.impl;

import com.mirbozorgi.business.exception.NotFoundException;
import com.mirbozorgi.business.domain.AvatarInfo;
import com.mirbozorgi.business.mapper.AvatarMapper;
import com.mirbozorgi.business.service.AvatarService;
import com.mirbozorgi.core.entity.Avatar;
import com.mirbozorgi.core.repository.entity.AvatarRepository;
import com.mirbozorgi.core.repository.entity.GameRepository;
import com.mirbozorgi.core.utilities.AvatarBuyType;
import com.mirbozorgi.core.utilities.CurrencyType;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class AvatarServiceImpl implements AvatarService {

  @Autowired
  private AvatarRepository avatarRepository;
  @Autowired
  private GameRepository gameRepository;


  @Override
  public AvatarInfo save(
      int gameId,
      String name,
      BigDecimal amount,
      CurrencyType currencyType,
      Boolean isActive,
      String avatarType,
      Boolean free,
      AvatarBuyType typeForBuy) {
    Avatar founded = avatarRepository.findByNameAndGameId(name, gameId);
    if (founded != null) {
      return AvatarMapper.toInfo(founded);
    }
    Avatar avatar = new Avatar(
        gameRepository.findById(gameId),
        name,
        amount,
        currencyType,
        isActive,
        avatarType,
        free,
        typeForBuy
    );
    return AvatarMapper.toInfo(avatarRepository.save(avatar));
  }

  @Override
  public AvatarInfo findById(long id) {
    return AvatarMapper.toInfo(avatarRepository.findById(id));
  }

  @Override
  public AvatarInfo findByNameAndGameId(String name, int gameId) {
    return AvatarMapper.toInfo(avatarRepository.findByNameAndGameId(
        name,
        gameId
    ));
  }

  @Override
  public List<AvatarInfo> findAll(String gamePackageName, String env) {
    List<AvatarInfo> avatarInfos = new ArrayList<>();
    List<Avatar> all = avatarRepository.findAll(gamePackageName, env);
    if (all == null) {
      return new ArrayList<>();
    }
    for (Avatar avatar : all) {
      avatarInfos.add(AvatarMapper.toInfo(avatar));
    }
    return avatarInfos;
  }

  @Transactional
  @Override
  public void delete(int id) {
    Avatar byId = avatarRepository.findById(id);
    if (byId == null) {
      throw new NotFoundException();
    }
    avatarRepository.delete(byId);
  }


  @Override
  public AvatarInfo update(
      int id,
      String name,
      BigDecimal amount,
      CurrencyType currencyType,
      Boolean isActive,
      String avatarType,
      Boolean free,
      AvatarBuyType typeForBuy) {
    Avatar avatarFounded = avatarRepository.findById(id);
    if (avatarFounded == null) {
      throw new NotFoundException();
    }
    if (name != null) {
      avatarFounded.setName(name);
    }
    if (amount != null) {
      avatarFounded.setAmount(amount);
    }
    if (isActive != null) {
      avatarFounded.setActive(isActive);

    }
    if (avatarType != null) {
      avatarFounded.setAvatarType(avatarType);
    }
    if (currencyType != null) {
      avatarFounded.setCurrencyType(currencyType);
    }
    if (free != null) {
      avatarFounded.setFree(free);
    }
    if (typeForBuy != null) {
      avatarFounded.setAvatarBuyType(typeForBuy);
    }

    return AvatarMapper.toInfo(avatarRepository.save(avatarFounded));
  }
}
