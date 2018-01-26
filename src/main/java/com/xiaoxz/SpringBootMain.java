package com.xiaoxz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


/**
 * @author : xiaoxz
 * @Date: Created in 2018/1/19
 * @Modified by :
 **/
@SpringBootApplication
@ComponentScan(basePackages = {"com.xiaoxz.dao","com.xiaoxz.service",
                                "com.xiaoxz.controller",
                                "com.xiaoxz.interceptor","com.xiaoxz.mail"})
public class SpringBootMain {

    public static void main(String[] args){
        SpringApplication.run(SpringBootMain.class, args);
    }
}
