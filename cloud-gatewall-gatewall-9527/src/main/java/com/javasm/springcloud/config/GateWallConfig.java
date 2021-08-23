package com.javasm.springcloud.config;


import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GateWallConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder){
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();

        routes.route("path_route_angenin",
                //  http://127.0.0.1:9527/guonei 访问此地址，跳转到 uri
                r -> r.path("/guonei")
                        .uri("http://news.baidu.com/guonei"));
        RouteLocator build = routes.build();
        return build;
    }

}
