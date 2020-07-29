package com.shadow.stan.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import reactor.core.publisher.Mono;

/**
 * @author cuipeng 2020/7/21 10:19
 */
@SpringBootApplication
public class StanGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(StanGatewayApplication.class, args);
    }

    @Bean
    public KeyResolver uriKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getPath().toString());
    }

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("rewrite_request_obj", r -> r.host("*.rewriterequestobj.org")
                        .filters(f -> f.prefixPath("/httpbin")
                                .modifyRequestBody(String.class, String.class, MediaType.APPLICATION_JSON_VALUE, );
    }
}
