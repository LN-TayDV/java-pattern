package com.spring.ctx.domain.chapter11.validation.validation.constraints;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
@ComponentScan(basePackages = {
    "com.spring.ctx.domain.chapter11.validation.validation.constraints"
})
public class JakartaValidationCfg {

    @Bean
    LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }
}
