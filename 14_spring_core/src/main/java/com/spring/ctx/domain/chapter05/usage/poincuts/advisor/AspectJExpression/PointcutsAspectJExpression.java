package com.spring.ctx.domain.chapter05.usage.poincuts.advisor.AspectJExpression;

import com.spring.ctx.domain.chapter05.usage.poincuts.advisor.PointcutsRgEx.Guitarist;
import com.spring.ctx.domain.chapter05.usage.poincuts.advisor.UsingDefaultPointcutAdvisor.SimpleAroundAdvice;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class PointcutsAspectJExpression {

    public static void main(String... args) {
        Guitarist johnMayer = new Guitarist();

        var pc = new AspectJExpressionPointcut();
        pc.setExpression("execution(* sing*(..))");
        var advisor = new DefaultPointcutAdvisor(pc, new SimpleAroundAdvice());

        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(johnMayer);
        pf.addAdvisor(advisor);

        Guitarist proxy = (Guitarist) pf.getProxy();
        proxy.sing();
        proxy.sing2();
        proxy.rest();
    }
}
