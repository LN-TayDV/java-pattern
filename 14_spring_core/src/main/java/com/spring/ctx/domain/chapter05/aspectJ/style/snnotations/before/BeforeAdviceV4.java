package com.spring.ctx.domain.chapter05.aspectJ.style.snnotations.before;

import com.spring.ctx.domain.chapter05.aspectJ.style.snnotations.items.Guitar;
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
public class BeforeAdviceV4 {

    @Pointcut("execution(* com.spring.ctx.domain.chapter05.aspectJ.style.snnotations.items..sing*(com.spring.ctx.domain.chapter05.aspectJ.style.snnotations.items.Guitar))")
    public void singExecution(Guitar value) {
    }

    @Pointcut("bean(john*)")
    public void isJohn() {
    }

    @Before(value = "singExecution(guitar) && isJohn()", argNames = "joinPoint,guitar")
    public void simpleBeforeAdvice(JoinPoint joinPoint, Guitar guitar) {
        if (guitar.getBrand().equals("Gibson")) {
            var signature = (MethodSignature) joinPoint.getSignature();
            LOGGER.info(" > Executing: {} from {}", signature.getName(),
                signature.getDeclaringTypeName());

        }
    }
}
