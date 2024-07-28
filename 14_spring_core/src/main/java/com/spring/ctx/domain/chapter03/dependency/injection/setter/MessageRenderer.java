package com.spring.ctx.domain.chapter03.dependency.injection.setter;

public interface MessageRenderer {
    void render();

    void setMessageProvider(MessageProvider provider);

    MessageProvider getMessageProvider();

}
