package com.github.skapral.poetryclub.db.operation;

import com.github.skapral.poetryclub.core.operation.Operation;
import com.github.skapral.poetryclub.db.access.DatabaseAccess;
import io.vavr.collection.List;
import org.jooq.DSLContext;
import org.jooq.Query;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.util.function.Supplier;

/**
 * Operation, executing a serie of jooq SQL statements on some database. All SQL statements are executed within one
 * transaction.
 *
 * @author Kapralov Sergey
 */
public class OpJooq implements Operation {
    private final DatabaseAccess access;
    private final List<Supplier<Query>> statememnts;

    /**
     * Ctor.
     * @param access Database access
     * @param statememnts SQL statements to execute
     */
    public OpJooq(DatabaseAccess access, List<Supplier<Query>> statememnts) {
        this.access = access;
        this.statememnts = statememnts;
    }

    /**
     * Ctor.
     * @param access Database access
     * @param statememnts SQL statements to execute
     */
    public OpJooq(DatabaseAccess access, Supplier<Query>... statememnts) {
        this(access, List.of(statememnts));
    }

    @Override
    public final void execute() {
        try(Connection conn = access.connection()) {
            try (DSLContext ctx = access.context(conn)) {
                ctx.transaction(conf -> {
                    statememnts.forEach(stmt -> DSL.using(conf).execute(stmt.get()));
                });
            }
        } catch(Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
