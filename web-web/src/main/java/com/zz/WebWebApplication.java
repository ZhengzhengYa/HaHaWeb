package com.zz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;


//@ComponentScan(basePackages = {"com.zz.dao","com.zz.controller","com.zz.service","com.zz.Filter"})
//@ComponentScan(basePackages = "com")
@SpringBootApplication
@MapperScan("com.zz.dao")
@ServletComponentScan(basePackages = "com.zz.Filter")
public class WebWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebWebApplication.class, args);
    }

}
