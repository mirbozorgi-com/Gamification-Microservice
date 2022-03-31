package com.mirbozorgi.business.service;

import com.mirbozorgi.business.domain.ApiResponse;
import java.util.function.Predicate;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;

public interface HttpClientService {

  <T> ApiResponse<T> restRequest(
      HttpMethod method,
      String url,
      Object body,
      MultiValueMap<String, String> headers,
      Class<T> classType
  );

  <T> ApiResponse<T> restRequest(
      HttpMethod method,
      String url,
      Object body,
      MultiValueMap<String, String> headers,
      Class<T> classType,
      Predicate<ApiResponse<T>> failPredicate
  );

  <T> ApiResponse<T> request(
      HttpMethod method,
      String url,
      Object body,
      MultiValueMap<String, String> headers,
      Class<T> classType,
      Predicate<ApiResponse<T>> failPredicate
  );
}
