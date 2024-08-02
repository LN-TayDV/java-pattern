package com.spring.ctx.domain.chapter05.aspectJ.style.snnotations.around;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class AroundAdviceV1 {

    @Pointcut("execution(* com.spring.ctx.domain.chapter05.aspectJ.style.snnotations.items..sing*(com.spring.ctx.domain.chapter05.aspectJ.style.snnotations.items.Guitar))")
    public void singExecution() {
    }

    @Around("singExecution()")
    public Object simpleAroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
        var signature = (MethodSignature) pjp.getSignature();
        LOGGER.info(" > Before Executing: {} from {}", signature.getName(), signature.getDeclaringTypeName() );
        Object retVal = pjp.proceed();
        LOGGER.info(" > After Executing: {} from {}", signature.getName(), signature.getDeclaringTypeName() );
        return retVal;
    }
}
