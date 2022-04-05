package com.mirbozorgi.zuul.filter;

import com.mirbozorgi.zuul.feigns.SecurityFeignService;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import feign.FeignException;
import java.util.LinkedHashMap;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.ProxyRequestHelper;


public class SimplePreFilter extends ZuulFilter {

  @Autowired
  private SecurityFeignService securityFeignService;


  @Override
  public String filterType() {
    return "pre";
  }

  @Override
  public int filterOrder() {
    return 1;
  }

  @Override
  public boolean shouldFilter() {
    return true;
  }

  @Override
  public Object run() {
    RequestContext ctx = RequestContext.getCurrentContext();

    //authorize token (user)
    HttpServletRequest request = ctx.getRequest();
    try {

      String token = request.getHeader("token");
      String marketName = request.getHeader("marketName");
      String env = request.getHeader("env");
      String gamePackageName = request.getHeader("gamePackageName");
      if (token != null) {
        if (!token.isEmpty()) {
          Object authorize = null;
          authorize = securityFeignService.authorize(token,
              marketName,
              gamePackageName,
              env);

          //find role and ect of user and set to request
          Object data = ((LinkedHashMap) authorize).get("data");
          String role = ((LinkedHashMap) data).get("role").toString();
          String uuid = ((LinkedHashMap) data).get("uuid").toString();

          ProxyRequestHelper helper = new ProxyRequestHelper();
          //delete token header from request
          helper.addIgnoredHeaders("token");
          ctx.getZuulRequestHeaders().remove("role");

          ctx.addZuulRequestHeader("role", role);
          ctx.addZuulRequestHeader("uuid", uuid);


        }
      }
    } catch (Exception e) {
      ctx.addZuulRequestHeader("exception", String.valueOf(((FeignException) e).status()));
      return null;
    }

    return null;
  }


}
