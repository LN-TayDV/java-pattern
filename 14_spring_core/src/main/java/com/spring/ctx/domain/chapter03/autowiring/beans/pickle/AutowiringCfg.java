package com.spring.ctx.domain.chapter03.autowiring.beans.pickle;

import com.spring.ctx.domain.chapter03.autowiring.beans.item.Bar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "com.spring.ctx.domain.chapter03.autowiring.beans.pickle"
})
public class AutowiringCfg {

    @Bean
    public Foo fooImplOne() { return new FooImplOne(); }

    @Bean
    public Foo fooImplTwo() { return new FooImplTwo(); }

    @Bean
    public Bar bar() { return new Bar(); }

    @Bean
    public TrickyTarget trickyTarget() { return new TrickyTarget(); }
}
