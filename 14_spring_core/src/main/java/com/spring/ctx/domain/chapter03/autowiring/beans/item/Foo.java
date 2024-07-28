package com.spring.ctx.domain.chapter03.autowiring.beans.item;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class Foo {

    public String id = UUID.randomUUID().toString().replace("-","").substring(0,8);
}
