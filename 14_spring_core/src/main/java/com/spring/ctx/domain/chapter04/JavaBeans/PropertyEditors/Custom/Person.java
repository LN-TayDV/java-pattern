package com.spring.ctx.domain.chapter04.JavaBeans.PropertyEditors.Custom;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Person {

    @Getter
    private FullName name;

    @Value("John Mayer")
    public void setName(FullName name) {
        this.name = name;
    }

}
