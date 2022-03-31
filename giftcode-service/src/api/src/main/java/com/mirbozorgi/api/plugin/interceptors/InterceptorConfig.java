package com.mirbozorgi.api.plugin.interceptors;

import mirbozorgi.base.plugin.interceptors.ApiFeignLoggerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class InterceptorConfig implements WebMvcConfigurer {


  @Autowired
  ApiFeignLoggerInterceptor apiLoggerInterceptor;


  @Value("${api.interceptor.api-logger}")
  private boolean apiLoggerEnable;


  @Override
  public void addInterceptors(InterceptorRegistry registry) {

    if (apiLoggerEnable) {
      registry.addInterceptor(apiLoggerInterceptor).addPathPatterns("/**")
          .excludePathPatterns("/health").excludePathPatterns("/docs");
    }

  }
}