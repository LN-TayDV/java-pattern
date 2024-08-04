package com.iluwatar.async.method.invocation.lamda.async.callback;

import lombok.Setter;
import java.util.Objects;
import java.util.function.Consumer;

public class AsyncCallback implements Callback {

    private Consumer<Object> onCompleteHandler;
    private Consumer<Exception> onErrorHandler;

    public AsyncCallback() {
    }

    private static final AsyncCallback instance = new AsyncCallback();

    public static AsyncCallback create() {
        return instance;
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