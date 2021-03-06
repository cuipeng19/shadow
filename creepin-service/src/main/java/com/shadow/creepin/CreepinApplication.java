package com.shadow.creepin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * creepin up on you
 * @author cuipeng 2019/12/19 10:50
 */
@SpringBootApplication
@EnableFeignClients
@MapperScan(basePackages = "com.shadow.creepin.dao")
public class CreepinApplication {

    public static void main(String[] args) {
        SpringApplication.run(CreepinApplication.class, args);
    }
}
