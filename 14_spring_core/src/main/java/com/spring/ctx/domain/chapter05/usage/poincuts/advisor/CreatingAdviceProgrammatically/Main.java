package com.spring.ctx.domain.chapter05.usage.poincuts.advisor.CreatingAdviceProgrammatically;

import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.util.StopWatch;
import java.lang.reflect.Method;

import static java.time.Duration.ofMillis;

@Slf4j
public class Main {

    public static void main(String... args) {
        Concert concert = new Concert();

        ProxyFactory pf = new ProxyFactory();

        //1
        pf.addAdvice(new SimpleBeforeAdvice());

        //2
        pf.addAdvice(new SimpleAroundAdvice());

        //4
        pf.addAdvice(new SimpleAfterAdvice());

        pf.setTarget(concert);

        Performance proxy = (Performance) pf.getProxy();

        //3
        proxy.execute();

        //5 SimpleAroundAdvice.invoke

    }


    public static class SimpleBeforeAdvice implements MethodBeforeAdvice {
        @Override
        public void before(Method method, Object[] args, Object target) throws Throwable {
            LOGGER.info("Before: set up concert hall.");
        }
    }

    public static class SimpleAfterAdvice implements AfterReturningAdvice {

        @Override
        public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
            LOGGER.info("After: offer standing ovation.");
        }
    }

    public static class SimpleAroundAdvice implements MethodInterceptor {

        @Override
        public Object invoke(@Nonnull MethodInvocation invocation) throws Throwable {
            LOGGER.info("Around: starting timer");

            StopWatch sw = new StopWatch();

            sw.start(invocation.getMethod().getName());

            Object returnValue = invocation.proceed();

            sw.stop();

            LOGGER.info("Around: concert duration = {}", sw.getTotalTimeMillis());

            return returnValue;
        }
    }

    public interface Performance {
        void execute();
    }

    public static class Concert implements Performance {

        @Override
        public void execute() {
            LOGGER.info(" ... La la la la laaaa ...");
            try {
                Thread.sleep(ofMillis(2000).toMillis());
            }catch (InterruptedException e) {

            }
        }
    }

}
