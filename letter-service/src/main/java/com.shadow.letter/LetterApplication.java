package com.shadow.letter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * luv letter
 * @author cuipeng 2020/7/7 16:45
 */
@SpringBootApplication
@EnableFeignClients
@MapperScan(basePackages = "com.shadow.letter.dao")
public class LetterApplication {

    public static void main(String[] args) {
        SpringApplication.run(LetterApplication.class, args);
    }
}
