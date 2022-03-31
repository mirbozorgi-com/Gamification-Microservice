package com.mirbozorgi.business.service.impl;


import com.mirbozorgi.business.service.StringService;
import org.apache.commons.codec.digest.DigestUtils;
import org.hashids.Hashids;
import org.springframework.stereotype.Service;

@Service
public class StringServiceImpl implements StringService {


  @Override
  public String toMd5(String value) {
    String md5Hex = DigestUtils.md5Hex(value).toUpperCase();
    return md5Hex;
  }

  @Override
  public Hashids getHashIds(String salt, int minSize) {
    return new Hashids(salt, minSize, "abcdefghijkmnpqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ123456789");
  }

}
