package com.spring.ctx.domain.chapter03.autowiring.beans.pickle;

import com.spring.ctx.domain.chapter03.autowiring.beans.constructor.Target;
import com.spring.ctx.domain.chapter03.bean.naming.BeanNamingCfg;
import com.spring.ctx.domain.chapter03.bean.naming.SimpleBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

@Slf4j
public class BeanNamingDemo {

    public static void main(String... args) {

        var ctx = new AnnotationConfigApplicationContext(AutowiringCfg.class);

        var target = ctx.getBean(TrickyTarget.class);

        log.info("Created Tricky Target? {}" , target != null);

        log.info("Injected TrickyTarget.bar? {}" , target.bar != null);

        log.info("Injected TrickyTarget.fooOne? {}" , target.fooOne != null ? target.fooOne: "null");

        log.info("Injected TrickyTarget.fooTwo? {}" , target.fooTwo != null ? target.fooTwo : "null");
    }
}
