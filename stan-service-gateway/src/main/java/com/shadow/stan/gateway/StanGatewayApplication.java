package com.shadow.stan.gateway;

import org.reactivestreams.Publisher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.factory.rewrite.RewriteFunction;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.server.ServerWebExchange;
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

//    @Bean
//    public RouteLocator routes(RouteLocatorBuilder builder) {
//        return builder.routes().route("rewrite-request-body", r -> r.host("*.rewriterequestobj.org")
//                .filters(f -> f.prefixPath("/stan")
//                .modifyRequestBody(String.class, String.class, MediaType.APPLICATION_JSON_VALUE, );
//    }
//
//    class RewriteRequestBody implements RewriteFunction<String,String> {
//
//        @Override
//        public Publisher<String> apply(ServerWebExchange exchange, String body) {
//            System.out.println("asdasdasd");
//            return Mono.just(body);
//        }
//    }
}
