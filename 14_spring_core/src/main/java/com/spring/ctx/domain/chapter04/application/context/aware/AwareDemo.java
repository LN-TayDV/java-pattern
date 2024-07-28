package com.spring.ctx.domain.chapter04.application.context.aware;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AwareDemo {

    public static void main(String... args) {
        new AnnotationConfigApplicationContext(AwareConfig.class);
    }
}
