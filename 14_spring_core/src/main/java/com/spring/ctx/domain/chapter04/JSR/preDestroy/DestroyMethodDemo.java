package com.spring.ctx.domain.chapter04.JSR.preDestroy;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DestroyMethodDemo {

    public static void main(String... args) {
        var ctx = new AnnotationConfigApplicationContext(JSRConfig.class);
        ctx.close(); // needed to close the context
    }
}
