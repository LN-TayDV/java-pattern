package com.spring.ctx.domain.chapter05.usage.poincuts.advisor.CreatingAnnotationMatchingPointcuts;

import com.spring.ctx.domain.chapter05.usage.poincuts.advisor.SimpleNameMatching.GrammyGuitarist;
import com.spring.ctx.domain.chapter05.usage.poincuts.advisor.UsingDefaultPointcutAdvisor.SimpleAroundAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;

public class AnnotationPointcutDemo {

    public static void main(String... args) {
        var johnMayer = new AnnotatedGuitarist();

        var pc = AnnotationMatchingPointcut.forMethodAnnotation(AdviceRequired.class);
        var advisor = new DefaultPointcutAdvisor(pc, new SimpleAroundAdvice());

        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(johnMayer);
        pf.addAdvisor(advisor);

        AnnotatedGuitarist proxy = (AnnotatedGuitarist) pf.getProxy();
        proxy.sing();
        proxy.sing(new GrammyGuitarist.Guitar());
        proxy.rest();
    }

}
