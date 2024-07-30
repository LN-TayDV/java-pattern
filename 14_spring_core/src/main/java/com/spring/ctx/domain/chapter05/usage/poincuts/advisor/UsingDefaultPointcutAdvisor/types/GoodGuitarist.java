package com.spring.ctx.domain.chapter05.usage.poincuts.advisor.UsingDefaultPointcutAdvisor.types;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GoodGuitarist implements Singer {
    @Override
    public void sing() {
        LOGGER.info("Head on your heart, arms around me");
    }
}
