package com.spring.ctx.domain.chapter03.autowiring.beans.byName;

import com.spring.ctx.domain.chapter03.autowiring.beans.item.Foo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "com.spring.ctx.domain.chapter03.autowiring.beans.byName"
        ,"com.spring.ctx.domain.chapter03.autowiring.beans.item"
})
public class AutowiringCfg {

    @Bean
    Foo anotherFoo() {
        return new Foo();
    }

}
