package com.mirbozorgi.core.repository.document;

import com.mirbozorgi.core.docuemnt.ItemShop;
import com.mirbozorgi.core.domain.ItemData;
import java.util.Map;

public interface ItemShopRepository {

  ItemShop save(ItemShop itemShop);

  ItemShop findById(String id);

  ItemShop update(
      String name,
      String gamePackageName,
      String env,
      String market,
      ItemData itemData);

  void delete(String name,
      String gamePackageName,
      String env,
      String market);

  Map<String, ItemData> findAll(
      String gamePackageName,
      String env,
      String market);

  ItemData findOne(
      String name,
      String gamePackageName,
      String env,
      String market);


  Boolean findEntity(
      String gamePackageName,
      String env,
      String market);


}
