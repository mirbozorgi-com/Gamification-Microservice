package com.mirbozorgi.core.repository.document;

import com.mirbozorgi.core.docuemnt.Spin;
import com.mirbozorgi.core.domain.ItemData;
import com.mirbozorgi.core.domain.SpinData;
import java.util.List;

public interface SpinRepository {

  Spin save(Spin spin);

  Spin findById(String id);

  Spin findSpin(
      String gamePackageName,
      String env,
      String market);


  Spin updatePeriodTime(
      String gamePackageName,
      String env,
      String market,
      String key,
      long periodTime);

  Spin deleteItem(
      String gamePackageName,
      String env,
      String market,
      String key,
      long periodTime);

  Boolean existKey(
      String gamePackageName,
      String env,
      String market,
      String key);

  List<String> getKeys(
      String gamePackageName,
      String env,
      String market);

  SpinData getAllByKey(
      String gamePackageName,
      String env,
      String market,
      String key);

  void update(
      String key,
      String gamePackageName,
      String env,
      String market,
      ItemData itemData);


  Spin updateItemSpin(String key,
      String gamePackageName,
      String env,
      String market,
      List<ItemData> itemDatas);
}
