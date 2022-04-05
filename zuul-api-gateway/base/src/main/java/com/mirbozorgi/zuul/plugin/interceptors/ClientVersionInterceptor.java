package com.mirbozorgi.zuul.plugin.interceptors;//package com.mirbozorgi.api.plugin.interceptors;
//
//import com.mirbozorgi.api.exception.ForceUpdateException;
//import com.mirbozorgi.api.exception.InvalidClientVersionException;
//import com.mirbozorgi.business.service.ConfigService;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
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