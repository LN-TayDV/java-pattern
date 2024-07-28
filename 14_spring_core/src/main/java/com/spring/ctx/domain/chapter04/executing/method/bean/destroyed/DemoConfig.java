package com.spring.ctx.domain.chapter04.executing.method.bean.destroyed;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.spring.ctx.domain.chapter04.executing.method.bean.destroyed")
public class DemoConfig {

    @Bean(destroyMethod = "destroyMethod")
    FileManager fileManager() {
        return new FileManager();
    }
}
