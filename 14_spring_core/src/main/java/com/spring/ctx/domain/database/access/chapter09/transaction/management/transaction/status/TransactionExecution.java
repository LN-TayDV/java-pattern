package com.spring.ctx.domain.database.access.chapter09.transaction.management.transaction.status;

public interface TransactionExecution {

    boolean isNewTransaction();

    void setRollbackOnly();

    boolean isRollbackOnly();

    boolean isCompleted();
}
