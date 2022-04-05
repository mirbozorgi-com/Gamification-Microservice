package com.mirbozorgi.business.service.impl;

import com.mirbozorgi.business.service.StringService;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class StringServiceImpl implements StringService {


  @Value("ab23JKLMNPhijkABCDEF895f8GHQRSTUmnABCDEpqrstuvwxyzVWXYZ1567895QRST")
  private String saltAlphabet;

  @Override
  public String toMd5(String value) {
    String md5Hex = DigestUtils.md5Hex(value).toUpperCase();
    return md5Hex;
  }

  @Override
  public String encodeBase64(String input) {
    return new String(Base64.encodeBase64(input.getBytes()));
  }

  @Override
  public String decodeBase64(String input) {
    return new String(Base64.decodeBase64(input.getBytes()));
  }

  @Override
  public String encodeBase64WithSalt(String input) {
    input = encodeBase64(input);
    return addSaltToString(10, input);
  }

  @Override
  public String decodeBase64WithSalt(String plain) {
    plain = getWithOutSalt(10, plain);
    return decodeBase64(plain);
  }

  @Override
  public String generateRandomString(boolean letter, boolean number, int length) {
    return RandomStringUtils.random(length, letter, number);
  }
//////////////////////////////////////
//////////private method////////////
  /////////////////////////////////

  private String addSaltToString(int numberOfSaltChar, String input) {
    String firstAdd = saltAlphabet.substring(0, numberOfSaltChar);
    String endAdd = saltAlphabet
        .substring(saltAlphabet.length() - numberOfSaltChar);
    return firstAdd + input + endAdd;
  }

  private String getWithOutSalt(int numberOfSaltChar, String input) {

    return input.substring(numberOfSaltChar,
        input.length() - numberOfSaltChar);
  }
}
