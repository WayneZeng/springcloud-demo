package com.demo.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@SpringBootApplication
@EnableZuulProxy
public class GatewayApplication {

  private static final Logger logger = LoggerFactory.getLogger(GatewayApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(GatewayApplication.class, args);
  }

  @Bean
  public RouterFunction<ServerResponse> routerFunction() {
    return RouterFunctions.route(
        RequestPredicates.path("/helloworld"),
        request -> ServerResponse.ok().body(BodyInserters.fromObject("helloworld")));
  }

  @Bean
  public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
    // 添加header头，便于鉴权
    return builder.routes()
        .route(r -> r.path("/header")
            .filters(f ->
                f.addResponseHeader("X-AnotherHeader", "another-header"))
            .uri("http://www.baidu.com")
        )
        .build();
  }
}
