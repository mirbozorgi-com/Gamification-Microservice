package com.mirbozorgi.springcloud.apigateway.filter;


import com.mirbozorgi.springcloud.apigateway.feigns.SecurityFeignService;
import java.util.LinkedHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class SimplePreFilter implements GlobalFilter {

  @Autowired
  private SecurityFeignService securityFeignService;
  final Logger logger = LoggerFactory.getLogger(SimplePreFilter.class);

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    ServerHttpRequest request = exchange.getRequest();
    HttpHeaders headers = request.getHeaders();
    String token = headers.getFirst("token");
    String gamePackageName = headers.getFirst("gamePackageName");
    String env = headers.getFirst("env");
    String marketName = headers.getFirst("marketName");
    if (token != null) {
      if (!token.isEmpty()) {
        Object authorize = null;
        try {
          authorize = securityFeignService.authorize(token,
              marketName,
              gamePackageName,
              env);
          //find role and ect of user and set to request
          Object data = ((LinkedHashMap) authorize).get("data");
          String role = ((LinkedHashMap) data).get("role").toString();
          String uuid = ((LinkedHashMap) data).get("uuid").toString();

          //add to headers
          request = request.mutate()
              .header("role", role)
              .build();
          request = request.mutate()
              .header("uuid", uuid)
              .build();
        } catch (Exception ignored) {
        }


      }
    }

    return chain.filter(exchange.mutate().request(request).build()).doOnError(
        (throwable -> logger.error("Failed for some reason at api gateway", throwable.getCause()))
    );

  }
}
