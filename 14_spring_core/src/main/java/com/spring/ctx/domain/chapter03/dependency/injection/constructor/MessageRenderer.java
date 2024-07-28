package com.spring.ctx.domain.chapter03.dependency.injection.constructor;

public interface MessageRenderer {
    void render();

    MessageProvider getMessageProvider();

    void setMessageProvider(MessageProvider provider);

}
