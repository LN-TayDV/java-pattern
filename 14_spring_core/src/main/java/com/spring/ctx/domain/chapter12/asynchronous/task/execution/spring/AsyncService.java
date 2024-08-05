package com.spring.ctx.domain.chapter12.asynchronous.task.execution.spring;

import java.util.concurrent.Future;

public interface AsyncService {

    void asyncTask ();

    Future<String> asyncWithReturn(String name);
}
