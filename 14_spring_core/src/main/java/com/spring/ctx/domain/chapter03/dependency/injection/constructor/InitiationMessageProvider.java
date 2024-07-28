package com.spring.ctx.domain.chapter03.dependency.injection.constructor;

import org.springframework.stereotype.Component;

@Component("provider01")
public class InitiationMessageProvider implements MessageProvider {

    public InitiationMessageProvider() {
        System.out.println(" --> InitiationMessageProvider: bean created");
    }

    @Override
    public String getMessage() {
        return "Hello World!";
    }
}
