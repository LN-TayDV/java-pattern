package com.spring.ctx.domain.chapter03.dependency.injection.constructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("renderer")
public class StandardOutMessageRenderer implements MessageRenderer {

    private MessageProvider messageProvider;

    /**
     * The
     *
     * @Autowired annotation is not used to decorate the constructor,
     * which tells Spring which constructor to use when
     * instantiating this bean,
     */
    @Autowired
    public StandardOutMessageRenderer(@Qualifier("provider02") MessageProvider messageProvider) {
        System.out.println(" --> StandardOutMessageRenderer: package constructor called");
        this.messageProvider = messageProvider;
    }

    @Override
    public void render() {
        if (messageProvider == null) {
            throw new RuntimeException(
                    "You must set the property messageProvider of class:" + StandardOutMessageRenderer.class.getName());
        }
        System.out.println(messageProvider.getMessage());
    }

    @Override
    public MessageProvider getMessageProvider() {
        return this.messageProvider;
    }

    @Override
    public void setMessageProvider(MessageProvider provider) {
        System.out.println(" --> StandardOutMessageRenderer: setting the provider");
        this.messageProvider = provider;
    }
}
