package com.spring.ctx.domain.chapter03.dependency.injection.setter;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class HelloWorldSpringAnnotated {


    public static void main(String... args) {
        // Sử dụng InitiationConfiguration thay vì HelloWorldConfiguration
        ApplicationContext ctx = new AnnotationConfigApplicationContext(InitiationConfiguration.class);

        MessageRenderer mr = ctx.getBean("renderer", MessageRenderer.class);

        mr.render();
    }
}
