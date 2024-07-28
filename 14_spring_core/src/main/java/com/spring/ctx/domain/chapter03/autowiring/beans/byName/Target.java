package com.spring.ctx.domain.chapter03.autowiring.beans.byName;

import com.spring.ctx.domain.chapter03.autowiring.beans.item.Bar;
import com.spring.ctx.domain.chapter03.autowiring.beans.item.Foo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
@Slf4j
public class Target {

    Foo fooOne;
    Foo fooTwo;
    Bar bar;

    @Autowired
    public Target(@Qualifier("foo") Foo foo) {
        this.fooOne = foo;
        log.info(" --> Target(Foo) called");
    }

    public Target(@Qualifier("foo")Foo foo, Bar bar) {
        this.fooOne = foo;
        this.bar = bar;
        log.info(" --> Target(Foo, Bar) called");
    }
}
