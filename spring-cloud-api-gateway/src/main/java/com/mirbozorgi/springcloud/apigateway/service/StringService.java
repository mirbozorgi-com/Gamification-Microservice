package com.mirbozorgi.springcloud.apigateway.service;


public interface StringService {

  String toMd5(String value);

  String encodeBase64(String input);

  String decodeBase64(String input);

}
