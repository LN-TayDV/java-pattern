/*
 * This project is licensed under the MIT license. Module model-view-viewmodel is using ZK framework licensed under LGPL (see lgpl-3.0.txt).
 *
 * The MIT License
 * Copyright © 2014-2022 Ilkka Seppälä
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.spring.ctx.domain.chapter05.framework.services.AOP;

import com.spring.ctx.domain.chapter05.usage.poincuts.advisor.SimpleNameMatching.GrammyGuitarist;
import org.aopalliance.aop.Advice;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AopConfig implements BeanFactoryAware {

    private BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Bean
    public GrammyGuitarist johnMayer() {
        return new GrammyGuitarist();
    }

    @Bean
    public Advice advice() {
        return new AuditAdvice();
    }

    @Bean
    public GrammyGuitarist proxyOne() {
        ProxyFactoryBean pfb = new ProxyFactoryBean();
        pfb.setProxyTargetClass(true);
        pfb.setTarget(johnMayer());
        pfb.setInterceptorNames("advice");
        pfb.setBeanFactory(beanFactory);
        pfb.setFrozen(true);
        return (GrammyGuitarist) pfb.getObject();
    }

    @Bean
    public Documentarist documentaristOne() {
        Documentarist documentarist = new Documentarist();
        documentarist.setDep(proxyOne());
        return documentarist;
    }

    @Bean
    public GrammyGuitarist proxyTwo() {
        ProxyFactoryBean pfb = new ProxyFactoryBean();
        pfb.setProxyTargetClass(true);
        pfb.setTarget(johnMayer());
        pfb.setInterceptorNames("advisor");
        pfb.setBeanFactory(beanFactory);
        pfb.setFrozen(true);
        return (GrammyGuitarist) pfb.getObject();
    }

    @Bean
    public Documentarist documentaristTwo() {
        Documentarist documentarist = new Documentarist();
        documentarist.setDep(proxyTwo());
        return documentarist;
    }

    @Bean
    public DefaultPointcutAdvisor advisor() {
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        advisor.setAdvice(advice());

        AspectJExpressionPointcut pc = new AspectJExpressionPointcut();
        pc.setExpression("execution(* sing*(..))");
        advisor.setPointcut(pc);

        return advisor;
    }

}
