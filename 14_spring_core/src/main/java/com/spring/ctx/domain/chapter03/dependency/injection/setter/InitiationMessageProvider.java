package com.spring.ctx.domain.chapter03.dependency.injection.setter;

import org.springframework.stereotype.Component;

@Component("provider")
public class InitiationMessageProvider implements MessageProvider {
    @Override
    public String getMessage() {
        return "Hello World!";
    }
}
