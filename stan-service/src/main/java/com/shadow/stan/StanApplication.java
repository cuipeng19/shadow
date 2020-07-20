package com.shadow.stan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * stan
 * @author cuipeng 2020/7/20 16:16
 */
@SpringBootApplication
@EnableFeignClients
@MapperScan(basePackages = "com.shadow.stan.dao")
public class StanApplication {

    public static void main(String[] args) {
        SpringApplication.run(StanApplication.class, args);
    }
}
