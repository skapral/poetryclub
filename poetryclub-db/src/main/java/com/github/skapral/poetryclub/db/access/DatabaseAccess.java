package com.github.skapral.poetryclub.db.access;

import org.jooq.DSLContext;

import java.sql.Connection;

/**
 * Database access
 *
 * @author Kapralov Sergey
 */
public interface DatabaseAccess {
    /**
     * @return Database connection.
     */
    Connection connection();

    /**
     * @param conn database's connection.
     * @return Jooq's {@link DSLContext} to the database.
     */
    DSLContext context(Connection conn);
}
