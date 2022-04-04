package com.mirbozorgi.business.service.impl;


import com.mirbozorgi.business.service.StringService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Service;

@Service
public class StringServiceImpl implements StringService {


  @Override
  public String toMd5(String value) {
    String md5Hex = DigestUtils.md5Hex(value).toUpperCase();
    return md5Hex;
  }



  @Override
  public String generateRandomString(boolean letter, boolean number, int length) {
    return RandomStringUtils.random(length, letter, number);
  }
}
