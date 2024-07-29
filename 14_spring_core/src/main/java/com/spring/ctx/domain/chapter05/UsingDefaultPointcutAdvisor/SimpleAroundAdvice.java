package com.spring.ctx.domain.chapter05.UsingDefaultPointcutAdvisor;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;


@Slf4j
public class SimpleAroundAdvice implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        LOGGER.info(">> Invoking " + invocation.getMethod().getName());
        Object retVal = invocation.proceed();
        LOGGER.info(">> Done");
        return retVal;
    }
}