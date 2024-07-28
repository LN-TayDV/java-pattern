package com.spring.ctx.domain.chapter04.executing.method.bean.destroyed;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DestroyMethodDemo {

    public static void main(String... args) {
        var ctx = new AnnotationConfigApplicationContext(DemoConfig.class);
        ctx.close(); // needed to close the context
    }
}
