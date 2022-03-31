package com.mirbozorgi.business.domain;

import org.springframework.http.HttpStatus;

public class ApiResponse<T> {

  private boolean success;

  private HttpStatus status;

  private T data;

  private String rawResponse;

  public ApiResponse() {
  }

  public ApiResponse(HttpStatus status, T data, String rawResponse) {
    this.status = status;
    this.data = data;
    this.rawResponse = rawResponse;
  }

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }

  public HttpStatus getStatus() {
    return status;
  }

  public void setStatus(HttpStatus status) {
    this.status = status;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public String getRawResponse() {
    return rawResponse;
  }

  public void setRawResponse(String rawResponse) {
    this.rawResponse = rawResponse;
  }
}
