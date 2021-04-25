package com.shadow.nancy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author cuipeng 2021/4/19 16:56
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.shadow.hhkb.sdk"})
@ComponentScan(basePackages = {"com.shadow"})
public class NancyApplication {

    public static void main(String[] args) {
        SpringApplication.run(NancyApplication.class, args);
    }
}
