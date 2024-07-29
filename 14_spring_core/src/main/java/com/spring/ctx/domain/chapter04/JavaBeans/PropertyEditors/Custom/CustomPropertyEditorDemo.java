package com.spring.ctx.domain.chapter04.JavaBeans.PropertyEditors.Custom;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class CustomPropertyEditorDemo {

    public static void main(String... args) {
        var ctx = new AnnotationConfigApplicationContext(CustomPropertyEditorCfg.class);

        var person = ctx.getBean(Person.class, "person");

        LOGGER.info("Person full name = {}" , person.getName());

        ctx.close();
    }
}
