package com.spring.ctx.domain.chapter05.UsingDefaultPointcutAdvisor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GreatGuitarist implements Singer {

    @Override public void sing() {
        LOGGER.info("You've got my soul in your hand");
    }
}