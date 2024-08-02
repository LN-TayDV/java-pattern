package com.spring.ctx.domain.chapter05.object.modification.detection;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.IntroductionAdvisor;
import org.springframework.aop.framework.ProxyFactory;

@Slf4j
public class IntroductionDemo {

    public static void main(String... args) {
        Contact target = new Contact();
        target.setName("John Mayer");

        IntroductionAdvisor advisor = new IsModifiedAdvisor();

        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(target);
        pf.addAdvisor(advisor);
        pf.setOptimize(true);

        Contact proxy = (Contact) pf.getProxy();
        IsModified proxyInterface = (IsModified)proxy;

        LOGGER.info("Is Contact? => {} " , (proxy instanceof Contact));
        LOGGER.info("Is IsModified? => {} " , (proxy instanceof IsModified));

        LOGGER.info("Has been modified? => {} " , proxyInterface.isModified());

        proxy.setName("John Mayer");
        LOGGER.info("Has been modified? => {} " , proxyInterface.isModified());

        proxy.setName("Ben Barnes");
        LOGGER.info("Has been modified? => {} " , proxyInterface.isModified());
    }
}
