package com.mirbozorgi.security.plugin;

import com.mirbozorgi.security.plugin.interceptors.LogBackInterceptor;
import mirbozorgi.base.plugin.interceptors.ApiFeignLoggerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {


  @Autowired
  ApiFeignLoggerInterceptor apiLoggerInterceptor;

  @Autowired
  LogBackInterceptor LockBackInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {

    register(registry, apiLoggerInterceptor, 1);
    register(registry, LockBackInterceptor, 5);
  }

  private void register(InterceptorRegistry registry, HandlerInterceptor interceptor, int order) {
    registry.addInterceptor(interceptor)
        .order(order)
        .addPathPatterns("/**")
        .excludePathPatterns("/health")
        .excludePathPatterns("/docs")
        .excludePathPatterns("/favicon.ico")
        .excludePathPatterns("/error")
        .excludePathPatterns("/log");
  }
}