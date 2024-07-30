package com.spring.ctx.domain.chapter05.usage.poincuts.advisor.PointcutsRgEx;

import com.spring.ctx.domain.chapter05.usage.poincuts.advisor.UsingDefaultPointcutAdvisor.types.Singer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Guitarist implements Singer {

    @Override
    public void sing() {
        LOGGER.info("Just keep me where the light is");
    }

    public void sing2() {
        LOGGER.info("And wrap me in your arms");
    }

    public void rest() {
        LOGGER.info("zzz...");
    }
}
