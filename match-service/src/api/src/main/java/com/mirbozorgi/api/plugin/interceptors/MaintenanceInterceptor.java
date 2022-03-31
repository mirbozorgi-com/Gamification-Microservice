package com.mirbozorgi.api.plugin.interceptors;//package com.mirbozorgi.api.plugin.interceptors;
//
//
//import com.mirbozorgi.api.exception.MaintenanceException;
//import com.mirbozorgi.business.service.ConfigService;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
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