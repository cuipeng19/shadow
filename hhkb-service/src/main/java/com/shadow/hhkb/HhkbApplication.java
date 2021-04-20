package com.shadow.hhkb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author cuipeng 2021/4/20 14:10
 */
@SpringBootApplication
@EnableDiscoveryClient
public class HhkbApplication {

    public static void main(String[] args) {
        SpringApplication.run(HhkbApplication.class, args);
    }
}
