package com.mirbozorgi.business.service.impl;

import com.mirbozorgi.business.domain.ApiResponse;
import com.mirbozorgi.business.service.HttpClientService;
import com.mirbozorgi.business.service.SerializerService;
import java.util.function.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class HttpClientServiceImpl implements HttpClientService {

  private static final Logger logger = LoggerFactory.getLogger(HttpClientServiceImpl.class);

  private final static RestTemplate restTemplate = new RestTemplate();

  private final SerializerService serializerService;

  public HttpClientServiceImpl(
      SerializerService serializerService
  ) {
    this.serializerService = serializerService;
  }

  @Override
  public <T> ApiResponse<T> restRequest(
      HttpMethod method,
      String url,
      Object body,
      MultiValueMap<String, String> headers,
      Class<T> classType

  ) {
    return restRequest(
        method,
        url,
        body,
        headers,
        classType,
        o -> !o.getStatus().is2xxSuccessful()
    );
  }

  @Override
  public <T> ApiResponse<T> restRequest(
      HttpMethod method,
      String url,
      Object body,
      MultiValueMap<String, String> headers,
      Class<T> classType,
      Predicate<ApiResponse<T>> failPredicate
  ) {

    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);

    if (headers != null) {
      httpHeaders.addAll(headers);
    }

    httpHeaders.addAll(headers);
    return request(
        method,
        url,
        body,
        headers,
        classType,
        failPredicate
    );
  }

  @Override
  public <T> ApiResponse<T> request(
      HttpMethod method,
      String url,
      Object body,
      MultiValueMap<String, String> headers,
      Class<T> classType,
      Predicate<ApiResponse<T>> failPredicate
  ) {
    ApiResponse result;

    HttpHeaders httpHeaders = new HttpHeaders();

    if (headers != null) {
      httpHeaders.addAll(headers);
    }

    HttpEntity<Object> httpEntity = new HttpEntity<>(
        body,
        httpHeaders
    );

    try {
      ResponseEntity<String> responseEntity =
          restTemplate.exchange(url
              , method
              , httpEntity,
              String.class
          );

      result = new ApiResponse<T>(
          responseEntity.getStatusCode(),
          serializerService.toObj(responseEntity.getBody(), classType),
          responseEntity.getBody()
      );

    } catch (HttpClientErrorException exp) {

      result = new ApiResponse<>(
          exp.getStatusCode(),
          serializerService.toObj(exp.getResponseBodyAsString(), classType),
          exp.getResponseBodyAsString()
      );

    } catch (Exception exp) {
      result = new ApiResponse<>(
          HttpStatus.INTERNAL_SERVER_ERROR,
          null,
          null
      );
    }

    if (failPredicate.test(result)) {

      MDC.put("HTTP_method", method.toString());
      MDC.put("HTTP_url", url);
      MDC.put("HTTP_body", serializerService.toJson(body));
      MDC.put("HTTP_header", headers == null ? "" : headers.toString());
      MDC.put("HTTP_response", result.getRawResponse());
      MDC.put("HTTP_status", result.getStatus().toString());
      logger.error(url);
    }

    return result;
  }
}

