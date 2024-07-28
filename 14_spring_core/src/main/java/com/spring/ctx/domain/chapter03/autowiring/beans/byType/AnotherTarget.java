package com.spring.ctx.domain.chapter03.autowiring.beans.byType;

import com.spring.ctx.domain.chapter03.autowiring.beans.item.Bar;
import com.spring.ctx.domain.chapter03.autowiring.beans.item.Foo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * When there are no constructors declared, but there are setters annotated with @Autowired,
 * Spring will use them and will identify the beans to be injected based on their type.
 * Listing 3-57 shows a class named AnotherTarget that is pretty similar to the Target class,
 * but the properties are injected using setters.
 */
@Component
@Lazy
@Slf4j
public class AnotherTarget {

    Foo fooOne;
    Foo fooTwo;
    Bar bar;

    @Autowired
    public void setFooOne(Foo fooOne) {
        log.info(" --> AnotherTarget#setFooOne(Foo) called");
        this.fooOne = fooOne;
    }
    @Autowired
    public void setFooTwo(Foo fooTwo) {
        log.info(" --> AnotherTarget#setFooTwo(Foo) called");
        this.fooTwo = fooTwo;
    }
    @Autowired
    public void setBar(Bar bar) {
        log.info(" --> AnotherTarget#setBar(Bar) called");
        this.bar = bar;
    }

}
