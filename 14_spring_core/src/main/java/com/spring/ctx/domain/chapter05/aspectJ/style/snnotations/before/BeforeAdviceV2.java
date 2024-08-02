package com.spring.ctx.domain.chapter05.aspectJ.style.snnotations.before;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class BeforeAdviceV2 {

    @Pointcut("execution(* com.spring.ctx.domain.chapter05.aspectJ.style.snnotations.items..sing*(com.spring.ctx.domain.chapter05.aspectJ.style.snnotations.items.Guitar))")
    public void singExecution() {
    }

    @Before("singExecution()")
    public void simpleBeforeAdvice(JoinPoint joinPoint) {
        var signature = (MethodSignature) joinPoint.getSignature();
        LOGGER.info(" > Executing: {} from {}", signature.getName(),
            signature.getDeclaringTypeName());
    }
}
