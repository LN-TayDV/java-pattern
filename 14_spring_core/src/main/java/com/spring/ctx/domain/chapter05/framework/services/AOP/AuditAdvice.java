package com.spring.ctx.domain.chapter05.framework.services.AOP;

import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.MethodBeforeAdvice;

@Slf4j
public class AuditAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        LOGGER.info("Executing {}", method);
    }
}
