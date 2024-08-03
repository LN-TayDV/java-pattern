package com.spring.ctx.domain.database.access.chapter09.transaction.management;

public class StaticTransactionDefinition implements TransactionDefinition {

    public static final TransactionDefinition INSTANCE = new StaticTransactionDefinition();

}
