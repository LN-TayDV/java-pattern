package com.spring.ctx.domain.database.access.chapter09.transaction.management.transaction.status;

import org.springframework.transaction.TransactionException;

public interface SavepointManager {

    Object createSavepoint() throws TransactionException;

    void rollbackToSavepoint(Object savepoint) throws TransactionException;

    void releaseSavepoint(Object savepoint) throws TransactionException;
}
