//package com.mirbozorgi.api.plugin.interceptors
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
//-service.api.plugin.interceptors;
//    -service.api.exception.MaintenanceException;
//    -service.business.service.ConfigService;
//
//@Component("maintenanceInterceptor")
//public class MaintenanceInterceptor extends HandlerInterceptorAdapter {
//
//  @Autowired
//  ConfigService configService;
//
//  @Override
//  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//      throws Exception {
//
//    if (configService.maintenance()) {
//      throw new MaintenanceException();
//    }
//
//    return true;
//  }
//}