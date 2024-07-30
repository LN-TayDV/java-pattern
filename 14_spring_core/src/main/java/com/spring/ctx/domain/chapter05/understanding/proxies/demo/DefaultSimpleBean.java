package com.spring.ctx.domain.chapter05.understanding.proxies.demo;

public class DefaultSimpleBean implements SimpleBean {

    @Override
    public void advised() {
        System.currentTimeMillis();
    }
    @Override
    public void unadvised() {
        System.currentTimeMillis();
    }
}
