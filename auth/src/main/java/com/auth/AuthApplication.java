package com.auth;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.ruoyi.common.security.annotation.EnableRyFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class, scanBasePackages = {"com.auth","com.common"})//取消数据源配置，以及事务管理且启动动态数据源
@EnableRyFeignClients(basePackages = "com.common")
@ServletComponentScan(basePackages = "com.common.filter")
public class AuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }

}
