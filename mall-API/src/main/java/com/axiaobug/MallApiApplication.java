package com.axiaobug;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @author Yanxiao
 * @version 0.1.0
 * @date 05 2021
 */
@SpringBootApplication
@EnableOpenApi
public class MallApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallApiApplication.class,args);
    }
}
