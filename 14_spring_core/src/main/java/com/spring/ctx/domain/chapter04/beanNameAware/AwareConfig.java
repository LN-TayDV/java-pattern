package com.spring.ctx.domain.chapter04.beanNameAware;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.spring.ctx.domain.chapter04.beanNameAware")
public class AwareConfig {

    @Bean
    NamedSinger johnMayer(){
        return new NamedSinger();
    }

    @Bean
    NamedSinger celineDion (){
        return new NamedSinger();
    }
}
