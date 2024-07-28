package com.spring.ctx.domain.chapter03.parameters.injection.nesting;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ParentConfig {

    @Bean
    public TitleProvider parentProvider(){
        return TitleProvider.instance(null);
    }
    @Bean
    public TitleProvider childProvider(){
        return TitleProvider.instance("Daughters");
    }

}
