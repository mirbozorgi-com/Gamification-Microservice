package com.mirbozorgi.business.domain;

import java.util.Map;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;

public class ApiRequest {


  private HttpMethod method;
  private String url;
  private Object body;
  private Map<String, ?> queryData;
  private MultiValueMap<String, String> headers;
  private Class<?> classType;


  public ApiRequest(HttpMethod method, String url, Object body,
      Map<String, ?> queryData,
      MultiValueMap<String, String> headers, Class<?> classType) {
    this.method = method;
    this.url = url;
    this.body = body;
    this.queryData = queryData;
    this.headers = headers;
    this.classType = classType;
  }

  public HttpMethod getMethod() {
    return method;
  }

  public String getUrl() {
    return url;
  }

  public Object getBody() {
    return body;
  }

  public Map<String, ?> getQueryData() {
    return queryData;
  }

  public MultiValueMap<String, String> getHeaders() {
    return headers;
  }

  public Class<?> getClassType() {
    return classType;
  }
}
