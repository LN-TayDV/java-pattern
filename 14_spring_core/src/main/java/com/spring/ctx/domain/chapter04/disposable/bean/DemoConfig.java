package com.spring.ctx.domain.chapter04.disposable.bean;

import com.spring.ctx.domain.chapter04.executing.method.bean.destroyed.FileManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.spring.ctx.domain.chapter04.disposable.bean")
public class DemoConfig {

    @Bean
    FileManager fileManager() {
        return new FileManager();
    }
}
