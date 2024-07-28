package com.spring.ctx.domain.chapter02;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloWorldConfiguration {

    @Bean// equivalent to <bean id="provider" class=".."/>
    public MessageProvider provider() {
        return new HelloWorldMessageProvider();
    }

    @Bean// equivalent to <bean id="renderer" class=".."/>
    public MessageRenderer renderer() {
        MessageRenderer renderer = new StandardOutMessageRenderer();
        renderer.setMessageProvider(provider());
        return renderer;
    }
}
