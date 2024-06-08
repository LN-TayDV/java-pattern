package com.iluwatar.async.method.invocation.lamda.async.callback;

import java.util.function.Consumer;

public interface Callback {

    Callback onComplete(Consumer<Object> value);

    Callback onError (Consumer<Exception> value);

    void complete (Object object);

    void error (Exception exception);
}
