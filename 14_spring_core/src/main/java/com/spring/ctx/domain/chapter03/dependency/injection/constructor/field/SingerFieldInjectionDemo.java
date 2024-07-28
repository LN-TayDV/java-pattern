package com.spring.ctx.domain.chapter03.dependency.injection.constructor.field;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingerFieldInjectionDemo {

    public static void main(String... args) {
        var ctx = new AnnotationConfigApplicationContext();

        ctx.register(Singer.class, Inspiration.class);
        ctx.refresh();

        Singer singerBean = ctx.getBean(Singer.class);
        singerBean.sing();
    }

}
