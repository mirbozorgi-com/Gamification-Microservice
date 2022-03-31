package mirbozorgi.base.plugin.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component("loggerFeignInterceptor")
public class ApiFeignLoggerInterceptor extends HandlerInterceptorAdapter {

  private static final Logger logger = LoggerFactory.getLogger(ApiFeignLoggerInterceptor.class);

  @Override
  public boolean preHandle(
      final HttpServletRequest request,
      final HttpServletResponse response,
      final Object handler) throws Exception {

    String url = request.getServletPath();
    if (!url.equals("/error")) {
      MDC.put("end_point", url);
    }

    String gamePackageName = request
        .getHeader("gamePackageName");

    String marketName = request.getHeader("marketName");

    String env = request
        .getHeader("env");

    logger.info(request.getServletPath());

    return true;
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
      Object object, Exception arg3)
      throws Exception {
    MDC.clear();
  }
}