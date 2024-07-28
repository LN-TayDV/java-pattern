package com.spring.ctx.domain.chapter04.FactoryBeans;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import java.security.MessageDigest;

public class MessageDigestFactoryBean implements FactoryBean<MessageDigest>, InitializingBean {

    private String algorithmName = "MD5";

    private MessageDigest messageDigest = null;

    @Override
    public MessageDigest getObject() throws Exception {
        return null;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return FactoryBean.super.isSingleton();
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
