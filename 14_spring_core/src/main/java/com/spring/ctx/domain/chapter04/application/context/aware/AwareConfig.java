package com.spring.ctx.domain.chapter04.application.context.aware;

import com.spring.ctx.domain.chapter04.JSR.preDestroy.FileManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class AwareConfig {

    @Bean
    FileManager fileManager() {
        return new FileManager();
    }
    @Bean
    ShutdownHookBean shutdownHookBean() {
        return new ShutdownHookBean();
    }
}
