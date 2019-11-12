package com.zhan.transaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class TransactionApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        // 设置启动类,用于独立tomcat运行的入口
        return application.sources(TransactionApplication.class);
    }
    public static void main(String[] args) {
        SpringApplication.run(TransactionApplication.class, args);
    }

}
