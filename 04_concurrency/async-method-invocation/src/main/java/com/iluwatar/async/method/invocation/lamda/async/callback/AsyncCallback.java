package com.iluwatar.async.method.invocation.lamda.async.callback;

import java.util.function.Consumer;

public class AsyncCallback implements Callback {

    private Consumer<Object> onCompleteHandler;
    private Consumer<Exception> onErrorHandler;

    public AsyncCallback() {
    }

    public static AsyncCallback create() {
        return new AsyncCallback();
    }

    @Override
    public Callback onComplete(Consumer<Object> value) {
        this.onCompleteHandler = value;
        return this;
    }

    @Override
    public Callback onError(Consumer<Exception> value) {
        this.onErrorHandler = value;
        return this;
    }

    @Override
    public void complete(Object object) {
        onCompleteHandler.accept(object);
    }

    @Override
    public void error(Exception exception) {
        onErrorHandler.accept(exception);
    }

}