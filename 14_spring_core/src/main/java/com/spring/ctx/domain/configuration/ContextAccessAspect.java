package com.spring.ctx.domain.configuration;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ContextAccessAspect {

    // Pointcut cho các phương thức trong package `adaptor`
    @Pointcut("execution(* com.spring.ctx.*.adaptor..*(..))")
    public void adaptorMethods() {}

    // Pointcut cho các phương thức trong package `pub`
    @Pointcut("execution(* com.spring.ctx.*.pub..*(..))")
    public void pubMethods() {}

    // Ràng buộc: chỉ cho phép adaptor gọi pub của các context
    @Before("adaptorMethods() && execution(* com.spring.ctx.*.pub..*(..))")
    public void checkAdaptorToPublic() {
        System.out.println("Valid: Adaptor can call Pub");
    }

    // Chặn truy cập từ các context khác (không phải adaptor) vào pub
    @Before("execution(* com.spring.ctx.*.pub..*(..)) && !adaptorMethods()")
    public void denyAccess() {
        throw new RuntimeException("Invalid access! Only adaptor can access pub methods of other contexts.");
    }

    // Chặn truy cập giữa các `app` `dom` và `infra` của các context khác
    @Before("execution(* com.spring.ctx.*.app..*(..)) || execution(* com.spring.ctx.*.infra..*(..)) || execution(* com.spring.ctx.*.dom..*(..))" )
    public void denyDirectAppOrInfraAccess() {
        throw new RuntimeException("Invalid access! Direct communication between contexts is not allowed. Use adaptor and pub.");
    }
}