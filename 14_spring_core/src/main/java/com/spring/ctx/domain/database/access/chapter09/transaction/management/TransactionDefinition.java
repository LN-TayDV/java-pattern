package com.spring.ctx.domain.database.access.chapter09.transaction.management;

import jakarta.annotation.Nullable;

import static org.springframework.transaction.TransactionDefinition.ISOLATION_DEFAULT;
import static org.springframework.transaction.TransactionDefinition.PROPAGATION_REQUIRED;
import static org.springframework.transaction.TransactionDefinition.TIMEOUT_DEFAULT;

public interface TransactionDefinition {

    default int getPropagationBehavior() {
        return PROPAGATION_REQUIRED; // 0
    }
    default int getIsolationLevel() {
        return ISOLATION_DEFAULT; // -1
    }
    default int getTimeout() {
        return TIMEOUT_DEFAULT; // -1
    }
    default boolean isReadOnly() {
        return false;
    }
    @Nullable
    default String getName() {
        return null;
    }
    // Return an unmodifiable {@code TransactionDefinition} with defaults.
    static TransactionDefinition withDefaults() {
        return StaticTransactionDefinition.INSTANCE;
    }
}
