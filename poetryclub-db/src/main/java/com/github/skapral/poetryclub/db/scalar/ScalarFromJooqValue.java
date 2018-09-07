package com.github.skapral.poetryclub.db.scalar;

import com.github.skapral.poetryclub.core.scalar.Scalar;
import com.github.skapral.poetryclub.db.access.DatabaseAccess;
import org.jooq.DSLContext;
import org.jooq.Record1;
import org.jooq.ResultQuery;

import java.sql.Connection;
import java.util.function.Supplier;

/**
 * Jooq's single value result
 * @param <T> Result's type
 *
 * @author Kapralov Sergey
 */
public class ScalarFromJooqValue<T> implements Scalar<T> {
    private final DatabaseAccess database;
    private final Supplier<ResultQuery<Record1<T>>> sql;

    /**
     * Ctor.
     * @param database Database access
     * @param sql SQL request
     */
    public ScalarFromJooqValue(DatabaseAccess database, Supplier<ResultQuery<Record1<T>>> sql) {
        this.database = database;
        this.sql = sql;
    }

    @Override
    public final T value() {
        try(Connection conn = database.connection()) {
            try (DSLContext context = database.context(conn)) {
                return context.fetchOne(sql.get()).component1();
            }
        } catch(Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
