package com.spring.ctx.domain.chapter04.beanNameAware;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanNameAware;

@Slf4j
public class NamedSinger implements BeanNameAware {
    private String name;

    @Override
    public void setBeanName(String name) {
        this.name = name;
    }

    public void sing() {
        log.info("Singer " + name + " is singing in London");
    }
}
