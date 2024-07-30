package com.spring.ctx.domain.chapter05.usage.poincuts.advisor.SimpleNameMatching;

import com.spring.ctx.domain.chapter05.usage.poincuts.advisor.UsingDefaultPointcutAdvisor.types.Singer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GrammyGuitarist implements Singer {

    @Override
    public void sing() {
        LOGGER.info("sing: Gravity is working against me\n" +
            "And gravity wants to bring me down");
    }

    public void sing(Guitar guitar) {
        LOGGER.info("play: " + guitar.play());
    }

    public void talk(){
        LOGGER.info("talk");
    }

    public void rest(){
        LOGGER.info("zzz");
    }

    public static class Guitar {
        public String play(){
            return "G C G C Am D7";
        }
    }
}
