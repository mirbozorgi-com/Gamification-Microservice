package com.mirbozorgi.security.service;


public interface StringService {

  String toMd5(String value);

  String encodeBase64(String input);

  String decodeBase64(String input);

  String encodeBase64WithSalt(String input);

  String decodeBase64WithSalt(String encrypted);

  String generateRandomString(boolean letter, boolean number, int length);


}
