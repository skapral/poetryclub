package com.github.skapral.poetryclub.db.scalar;

import com.github.skapral.poetryclub.core.scalar.Scalar;
import com.github.skapral.poetryclub.db.access.DatabaseAccess;
import org.jooq.*;

import java.sql.Connection;
import java.util.*;
import java.util.function.Supplier;

/**
 * Jooq SQL request result
 *
 * @author Kapralov Sergey
 */
public class ScalarFromJooqRecords implements Scalar<List<Map<String, Object>>> {
    private final DatabaseAccess database;
    private final Supplier<ResultQuery> sql;

    /**
     * Ctor.
     * @param database Database access
     * @param sql SQL request
     */
    public ScalarFromJooqRecords(DatabaseAccess database, Supplier<ResultQuery> sql) {
        this.database = database;
        this.sql = sql;
    }

    @Override
    public final List<Map<String, Object>> value() {
        try(Connection conn = database.connection()) {
            try (DSLContext context = database.context(conn)) {
                Results results = context.fetchMany(sql.get());
                List<Map<String, Object>> v = new ArrayList<>();
                for (ResultOrRows rr : results.resultsOrRows()) {
                    for (Record record : rr.result()) {
                        Map<String, Object> p = new HashMap<>();
                        for (Field field : record.fields()) {
                            p.put(field.getName(), record.get(field));
                        }
                        v.add(Collections.unmodifiableMap(p));
                    }
                }
                return Collections.unmodifiableList(v);
            }
        } catch(Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
