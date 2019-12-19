package com.shadow.creepin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author cuipeng 2019/12/19 10:50
 */
@SpringBootApplication
@EnableDiscoveryClient
public class CreepinApplication {

    public static void main(String[] args) {
        SpringApplication.run(CreepinApplication.class, args);
    }
}
