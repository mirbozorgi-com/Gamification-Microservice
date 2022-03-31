package com.mirbozorgi.business.service;

public interface LeaderBoardMemoryXpService {


  void add(
      String packageName,
      String env,
      String market,
      long score,
      String uuid);

  void update(
      String packageName,
      String env,
      String market,
      long score,
      String uuid);

  Long getPosition(
      String packageName,
      String env,
      String market,
      String uuid);


}
