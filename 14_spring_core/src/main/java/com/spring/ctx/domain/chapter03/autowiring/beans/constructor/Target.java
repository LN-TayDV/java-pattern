package com.spring.ctx.domain.chapter03.autowiring.beans.constructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Lazy
public class Target {

    private static final Logger logger = LoggerFactory.getLogger(Target.class);

    Foo fooOne;
    Foo fooTwo;
    Bar bar;

    public Target() {
        logger.info(" --> Target() called");
    }

    public Target(Foo foo) {
        this.fooOne = foo;
        logger.info(" --> Target(Foo) called");
    }

    public Target(Foo foo, Bar bar) {
        this.fooOne = foo;
        this.bar = bar;
        logger.info(" --> Target(Foo, Bar) called");
    }

    @Autowired
    public void setFooOne(Foo fooOne) {
        logger.info(" --> AnotherTarget#setFooOne(Foo) called");
        this.fooOne = fooOne;
    }
    @Autowired
    public void setFooTwo(Foo fooTwo) {
        logger.info(" --> AnotherTarget#setFooTwo(Foo) called");
        this.fooTwo = fooTwo;
    }
    @Autowired
    public void setBar(Bar bar) {
        logger.info(" --> AnotherTarget#setBar(Bar) called");
        this.bar = bar;
    }

    @Component
    static class Foo {
        String id = UUID.randomUUID().toString().replace("-","").substring(0,8);
    }

    @Component
    static class Bar {}
}
