package com.spring.ctx.domain.chapter05.usage.poincuts.advisor.CreatingAnnotationMatchingPointcuts;

import com.spring.ctx.domain.chapter05.usage.poincuts.advisor.SimpleNameMatching.GrammyGuitarist;
import com.spring.ctx.domain.chapter05.usage.poincuts.advisor.UsingDefaultPointcutAdvisor.types.Singer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AnnotatedGuitarist implements Singer {

    @Override
    public void sing() {
        LOGGER.info("play:  but nothing show ");
    }

    @AdviceRequired
    public void sing(GrammyGuitarist.Guitar guitar) {
        LOGGER.info("play: " + guitar.play());
    }

    public void rest(){
        LOGGER.info("zzz");
    }
}
