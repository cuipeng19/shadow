package com.shadow.stan.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
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

}
