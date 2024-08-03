package com.spring.ctx.domain.database.access.chapter06.jdbc.connection;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DeadlockLoserDataAccessException;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import java.sql.SQLException;

public class PostgresDbErrorCodesTranslator extends SQLErrorCodeSQLExceptionTranslator {
    @Override
    protected DataAccessException doTranslate(String task, String sql, SQLException ex) {
        if (ex.getErrorCode() == -12345) {
            return new PessimisticLockingFailureException(task, ex);
        }
        return null;
    }
}
