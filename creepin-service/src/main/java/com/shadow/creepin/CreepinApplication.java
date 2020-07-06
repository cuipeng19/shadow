package com.shadow.creepin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author cuipeng 2019/12/19 10:50
 */
@SpringBootApplication
@MapperScan(basePackages = "com.shadow.creepin.dao")
public class CreepinApplication {

    public static void main(String[] args) {
        SpringApplication.run(CreepinApplication.class, args);
    }
}
