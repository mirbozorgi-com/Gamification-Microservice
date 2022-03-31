package mirbozorgi.base.feignService;

import java.io.IOException;

public interface SerializerFeignService {

  <T> String toJson(T obj);

  <T> T toObj(String value, Class<T> classType);

  <T> T toObjectFromFeign(Object o, Class<T> classType);

  <T> String mergeJson(String obj1, String obj2) throws IOException;
}
