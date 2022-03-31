//package com.mirbozorgi.api.plugin.interceptors
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
//-service.api.plugin.interceptors;
//    -service.api.exception.ForceUpdateException;
//    -service.api.exception.InvalidClientVersionException;
//    -service.business.service.ConfigService;
//
//@Component("clientversioninterceptor")
//public class ClientVersionInterceptor extends HandlerInterceptorAdapter {
//
//  private static final String CLIENT_VERSION_HEADER = "client-version";
//
//  @Autowired
//  ConfigService configService;
//
//  @Override
//  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//      throws Exception {
//
//    String clientVersionRaw = request.getHeader(CLIENT_VERSION_HEADER);
//
//    if (StringUtils.isEmpty(clientVersionRaw)) {
//      throw new InvalidClientVersionException();
//    }
//
//    Integer clientVersion = 0;
//
//    try {
//      clientVersion = Integer.parseInt(clientVersionRaw);
//    } catch (Exception exp) {
//      throw new InvalidClientVersionException();
//    }
//
//    if (clientVersion < configService.clientVersion()) {
//      throw new ForceUpdateException();
//    }
//
//    return true;
//  }
//}