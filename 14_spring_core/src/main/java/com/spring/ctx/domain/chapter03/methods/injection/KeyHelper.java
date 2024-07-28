package com.spring.ctx.domain.chapter03.methods.injection;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Lookup Method Injection works by having your singleton declare a method,
 * the lookup method, which returns an instance of the non-singleton bean.
 * When you obtain a reference to the singleton in your application,
 * you are actually receiving a reference to a dynamically created subclass on which Spring
 * has implemented the lookup method
 */
@Component("keyHelper")
@Scope("prototype")
public class KeyHelper {

    public void open(){
    }

}
