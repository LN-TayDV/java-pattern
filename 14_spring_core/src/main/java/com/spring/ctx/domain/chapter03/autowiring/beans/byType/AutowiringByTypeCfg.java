package com.spring.ctx.domain.chapter03.autowiring.beans.byType;

import com.spring.ctx.domain.chapter03.autowiring.beans.item.Foo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "com.spring.ctx.domain.chapter03.autowiring.beans.byType",
        "com.spring.ctx.domain.chapter03.autowiring.beans.item"
})
public class AutowiringByTypeCfg {

}
