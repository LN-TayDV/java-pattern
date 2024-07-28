package com.spring.ctx.domain.chapter03.autowiring.beans.constructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class AutowiringDemo {


    public static void main(String... args) {
        var ctx = new AnnotationConfigApplicationContext(AutowiringCfg.class);

        var target = ctx.getBean(Target.class);

        log.info("Created target? {}" , target != null);

        log.info("Injected bar? {}" , target.bar != null);

        log.info("Injected fooOne.id? {}" , target.fooOne != null ? target.fooOne.id: "null");

        log.info("Injected fooTwo.id? {}" , target.fooTwo != null ? target.fooTwo.id : "null");
    }

}
