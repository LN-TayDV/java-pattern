package com.spring.ctx.domain.chapter05.understanding.proxies.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.NameMatchMethodPointcutAdvisor;

@Slf4j
public class ProxyPerfTestDemo {

    public static void main(String... args) {
        SimpleBean target = new DefaultSimpleBean();
        NameMatchMethodPointcutAdvisor advisor = new NameMatchMethodPointcutAdvisor(new NoOpBeforeAdvice());
        advisor.setMappedName("advised");
        LOGGER.info("Starting tests ...");
        runCglibTests(advisor, target);
        runCglibFrozenTests(advisor, target);
        runJdkTests(advisor, target);
    }

    private static void runCglibTests(Advisor advisor, SimpleBean target) {
        ProxyFactory pf = new ProxyFactory();
        pf.setProxyTargetClass(true);
        pf.setTarget(target);
        pf.addAdvisor(advisor);
        SimpleBean proxy = (SimpleBean)pf.getProxy();
        var testResults = test(proxy);
        LOGGER.info(" --- CGLIB (Standard) Test results --- {} ", testResults);
    }

    private static void runCglibFrozenTests(Advisor advisor, SimpleBean target) {
        ProxyFactory pf = new ProxyFactory();
        pf.setProxyTargetClass(true);
        pf.setTarget(target);
        pf.addAdvisor(advisor);
        pf.setFrozen(true);
        SimpleBean proxy = (SimpleBean) pf.getProxy();
        var testResults = test(proxy);
        LOGGER.info(" --- CGLIB (Frozen) Test results ---\n {} ", testResults);
    }

    private static void runJdkTests(Advisor advisor, SimpleBean target) {
        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(target);
        pf.addAdvisor(advisor);
        pf.setInterfaces(SimpleBean.class);
        SimpleBean proxy = (SimpleBean)pf.getProxy();
        var testResults = test(proxy);
        LOGGER.info(" --- JDK Test results ---\n {} ", testResults);
    }

    private static  TestResults test(SimpleBean bean) {
        TestResults testResults = new TestResults();
        //--------
        long before = System.currentTimeMillis();
        for(int x = 0; x < 500000; x++) {
            bean.advised();
        }
        long after = System.currentTimeMillis();
        testResults.advisedMethodTime = after - before;
        //-----
        before = System.currentTimeMillis();
        for(int x = 0; x < 500000; x++) {
            bean.unadvised();
        }
        after = System.currentTimeMillis();
        testResults.unadvisedMethodTime = after - before;
        //-----
        before = System.currentTimeMillis();
        for(int x = 0; x < 500000; x++) {
            bean.equals(bean);
        }
        after = System.currentTimeMillis();
        testResults.equalsTime = after - before;
        // ----
        before = System.currentTimeMillis();
        for(int x = 0; x < 500000; x++) {
            bean.hashCode();
        }
        after = System.currentTimeMillis();
        testResults.hashCodeTime = after - before;
        // -----
        Advised advised = (Advised)bean;
        before = System.currentTimeMillis();
        for(int x = 0; x < 500000; x++) {
            advised.getTargetClass();
        }
        after = System.currentTimeMillis();
        testResults.proxyTargetTime = after - before;
        return testResults;
    }
}
