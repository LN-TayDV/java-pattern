package com.spring.ctx.domain.chapter03.autowiring.beans.byType;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class AutowiringDemo {


    public static void main(String... args) {
        var ctx = new AnnotationConfigApplicationContext(AutowiringByTypeCfg.class);

        var anotherTarget = ctx.getBean(AnotherTarget.class);

        log.info("anotherTarget: Created anotherTarget? {}" , anotherTarget != null);
        log.info("anotherTarget: Injected bar? {}" , anotherTarget.bar != null);
        log.info("anotherTarget: Injected fooOne? {}" , anotherTarget.fooOne != null ? anotherTarget.fooOne.id: "");
        log.info("anotherTarget: Injected fooTwo? {}" , anotherTarget.fooTwo != null ? anotherTarget.fooTwo.id : "");
    }
}
