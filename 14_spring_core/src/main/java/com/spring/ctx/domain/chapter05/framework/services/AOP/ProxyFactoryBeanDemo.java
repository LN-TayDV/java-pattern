package com.spring.ctx.domain.chapter05.framework.services.AOP;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ProxyFactoryBeanDemo {

    public static void main(String... args) {
        var ctx = new AnnotationConfigApplicationContext(AopConfig.class);

        Documentarist documentaristOne = ctx.getBean("documentaristOne", Documentarist.class);
        Documentarist documentaristTwo = ctx.getBean("documentaristTwo", Documentarist.class);

        System.out.println("Documentarist One >>");
        documentaristOne.execute();

        System.out.println("\nDocumentarist Two >> ");
        documentaristTwo.execute();
    }
}
