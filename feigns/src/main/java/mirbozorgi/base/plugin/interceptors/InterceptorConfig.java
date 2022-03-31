package mirbozorgi.base.plugin.interceptors;//package com.mirbozorgi.api.plugin.interceptors;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class InterceptorConfig implements WebMvcConfigurer {
//
//
//  @Autowired
//  ApiFeignLoggerInterceptor apiLoggerInterceptor;
//
//  @Autowired
//  MaintenanceInterceptor maintenanceInterceptor;
//
//  @Autowired
//  ClientVersionInterceptor clientVersionInterceptor;
//
//  @Autowired
//  MarketInterceptor marketInterceptor;
//
//
//  @Value("${api.interceptor.market}")
//  private boolean marketEnable;
//
//  @Value("${api.interceptor.client-version}")
//  private boolean clientVersionEnable;
//
//  @Value("${api.interceptor.api-logger}")
//  private boolean apiLoggerEnable;
//
//
//  @Override
//  public void addInterceptors(InterceptorRegistry registry) {
//
//    registry.addInterceptor(maintenanceInterceptor).addPathPatterns("/**")
//        .excludePathPatterns("/health").excludePathPatterns("/docs");
//
//    if (clientVersionEnable) {
//      registry.addInterceptor(clientVersionInterceptor).addPathPatterns("/**")
//          .excludePathPatterns("/health").excludePathPatterns("/docs");
//    }
//
//    if (marketEnable) {
//      registry.addInterceptor(marketInterceptor).addPathPatterns("/**")
//          .excludePathPatterns("/health")
//          .excludePathPatterns("/docs");
//    }
//
//    if (apiLoggerEnable) {
//      registry.addInterceptor(apiLoggerInterceptor).addPathPatterns("/**")
//          .excludePathPatterns("/health").excludePathPatterns("/docs");
//    }
//
//  }
//}