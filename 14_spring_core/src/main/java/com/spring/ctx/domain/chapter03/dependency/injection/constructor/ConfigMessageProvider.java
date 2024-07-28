package com.spring.ctx.domain.chapter03.dependency.injection.constructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("provider02")
public class ConfigMessageProvider implements MessageProvider{

    private final String message;

    public ConfigMessageProvider(@Value("Configurable message") String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
