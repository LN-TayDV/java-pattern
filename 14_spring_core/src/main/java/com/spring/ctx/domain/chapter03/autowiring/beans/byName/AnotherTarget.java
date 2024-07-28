package com.spring.ctx.domain.chapter03.autowiring.beans.byName;

import com.spring.ctx.domain.chapter03.autowiring.beans.item.Bar;
import com.spring.ctx.domain.chapter03.autowiring.beans.item.Foo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Lazy
public class AnotherTarget {

     Foo fooOne;
     Foo fooTwo;
     Bar bar;

    @Autowired
    public void setFooOne(@Qualifier("foo")Foo fooOne) {
        log.info(" --> AnotherTarget#setFooOne(Foo) called");
        this.fooOne = fooOne;
    }

    @Autowired
    public void setFooTwo(@Qualifier("anotherFoo")Foo fooTwo) {
        log.info(" --> AnotherTarget#setFooTwo(Foo) called");
        this.fooTwo = fooTwo;
    }

    @Autowired
    public void setBar(Bar bar) {
        log.info(" --> AnotherTarget#setBar(Bar) called");
        this.bar = bar;
    }
}
