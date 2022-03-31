package com.mirbozorgi.business.service;

public interface ConfigService {

  Integer clientVersion();

  boolean maintenance();

  Integer[] cohortChance();
}