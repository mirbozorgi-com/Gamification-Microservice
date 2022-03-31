package com.mirbozorgi.business.service;

import org.hashids.Hashids;


public interface StringService {

  String toMd5(String value);

  Hashids getHashIds(String salt, int minSize);

}
