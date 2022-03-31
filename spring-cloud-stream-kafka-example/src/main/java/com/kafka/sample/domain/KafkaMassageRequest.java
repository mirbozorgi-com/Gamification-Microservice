package com.kafka.sample.domain;

public class KafkaMassageRequest {

  private ApiRequest apiRequest;

  private String serviceName;

  private long time;

  public KafkaMassageRequest(ApiRequest apiRequest, String serviceName, long time) {
    this.apiRequest = apiRequest;
    this.serviceName = serviceName;
    this.time = time;
  }

  public void setApiRequest(ApiRequest apiRequest) {
    this.apiRequest = apiRequest;
  }

  public void setServiceName(String serviceName) {
    this.serviceName = serviceName;
  }

  public void setTime(long time) {
    this.time = time;
  }

  public ApiRequest getApiRequest() {
    return apiRequest;
  }

  public String getServiceName() {
    return serviceName;
  }

  public long getTime() {
    return time;
  }
}
