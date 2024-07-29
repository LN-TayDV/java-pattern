package com.spring.ctx.domain.chapter04.EventMessageSource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageEventListener implements ApplicationListener<MessageEvent> {

    @Override
    public void onApplicationEvent(MessageEvent event) {

        MessageEvent msgEvt = event;
        LOGGER.info("Received: {}" , event.getMsg());
    }
}
