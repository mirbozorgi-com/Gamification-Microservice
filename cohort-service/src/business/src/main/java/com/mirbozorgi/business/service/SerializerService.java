package com.mirbozorgi.business.service;

import java.io.IOException;

public interface SerializerService {

  <T> String toJson(T obj);

  <T> T toObj(String value, Class<T> classType);

  <T> T toObjectFromFeign(Object o, Class<T> classType);

  <T> String mergeJson(String obj1, String obj2) throws IOException;
}
