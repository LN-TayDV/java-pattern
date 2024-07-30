package com.spring.ctx.domain.chapter05.usage.poincuts.advisor.UsingDefaultPointcutAdvisor;

import com.spring.ctx.domain.chapter05.usage.poincuts.advisor.UsingDefaultPointcutAdvisor.types.GoodGuitarist;
import com.spring.ctx.domain.chapter05.usage.poincuts.advisor.UsingDefaultPointcutAdvisor.types.Singer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.DynamicMethodMatcherPointcut;
import java.lang.reflect.Method;

@Slf4j
public class DynamicMethodMatcherPointcutMain {

    public static void main(String... args) {
        GoodGuitarist target = new GoodGuitarist();
        Advisor advisor = new DefaultPointcutAdvisor(new SimpleDynamicPointcut(), new SimpleAroundAdvice());

        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(target);
        pf.addAdvisor(advisor);

        Singer proxy = (Singer)pf.getProxy();
        proxy.sing("C");
        proxy.sing("c");
        proxy.sing("E");
        proxy.sing();
    }

    static class SimpleDynamicPointcut extends DynamicMethodMatcherPointcut {

        @Override
        public ClassFilter getClassFilter() {
            return  cls -> (cls == GoodGuitarist.class);
        }

        @Override
        public boolean matches(Method method, Class<?> targetClass) {
            return ("sing".equals(method.getName()));
        }

        @Override
        public boolean matches(Method method, Class<?> targetClass, Object... args) {
            LOGGER.info("Dynamic check for " + method.getName());

            if(args.length == 0) {
                return false;
            }

            var key = (String) args[0];

            return key.equalsIgnoreCase("C");
        }
    }
}
