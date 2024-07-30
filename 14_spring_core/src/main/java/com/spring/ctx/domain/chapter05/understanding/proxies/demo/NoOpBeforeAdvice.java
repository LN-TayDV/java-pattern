package com.spring.ctx.domain.chapter05.understanding.proxies.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.MethodBeforeAdvice;
import java.lang.reflect.Method;

@Slf4j
public class NoOpBeforeAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        LOGGER.info("Before : but not thing show");
    }
}
