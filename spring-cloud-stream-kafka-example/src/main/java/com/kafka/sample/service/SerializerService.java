package com.kafka.sample.service;

public interface SerializerService {

  <T> String toJson(T obj);

  <T> T toObj(String value, Class<T> classType);

  <T> T toObjectFromFeign(Object o, Class<T> classType);


}
