package com.mirbozorgi.security.plugin.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component("logbackInterceptor")
public class LogBackInterceptor extends HandlerInterceptorAdapter {


  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
      Object object, Exception arg3)
      throws Exception {

    MDC.clear();
  }

}
