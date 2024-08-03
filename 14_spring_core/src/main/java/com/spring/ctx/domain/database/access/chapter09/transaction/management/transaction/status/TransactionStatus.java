package com.spring.ctx.domain.database.access.chapter09.transaction.management.transaction.status;

import java.io.Flushable;

public interface TransactionStatus extends TransactionExecution, SavepointManager, Flushable {

    boolean hasSavepoint();

    @Override
    void flush();
}
