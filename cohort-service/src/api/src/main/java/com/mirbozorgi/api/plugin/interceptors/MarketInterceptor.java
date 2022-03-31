//package com.mirbozorgi.api.plugin.interceptors;
//
//import com.mirbozorgi.api.exception.InvalidMarketException;
//import com.mirbozorgi.business.service.ConfigService;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
//@Component("marketinterceptor")
//public class MarketInterceptor extends HandlerInterceptorAdapter {
//
//  private static final String MARKET_HEADER = "market";
//
//  @Autowired
//  ConfigService configService;
//
//
//  @Override
//  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//      throws Exception {
//
//    String marketRaw = request.getHeader(MARKET_HEADER);
//
//    if (StringUtils.isEmpty(marketRaw)) {
//      throw new InvalidMarketException();
//    }
//
//    request.getSession().setAttribute("market-id", marketRaw);
//
//    return true;
//  }
//}