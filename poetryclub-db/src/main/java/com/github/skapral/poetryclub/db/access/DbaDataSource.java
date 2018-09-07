package com.github.skapral.poetryclub.db.access;

import com.github.skapral.poetryclub.core.scalar.Scalar;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.conf.RenderNameStyle;
import org.jooq.conf.Settings;
import org.jooq.impl.DSL;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * Database access by provided data source
 *
 * @author Kapralov Sergey
 */
public class DbaDataSource implements DatabaseAccess {
    private final Scalar<DataSource> dataSource;

    /**
     * Ctor.
     * @param dataSource Data source
     */
    public DbaDataSource(Scalar<DataSource> dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public final Connection connection() {
        try {
            Connection connection = dataSource.value().getConnection();
            return connection;
        } catch(Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public final DSLContext context(Connection conn) {
        Settings settings = new Settings()
            .withRenderNameStyle(RenderNameStyle.UPPER)
            .withRenderSchema(false);
        return DSL.using(conn, SQLDialect.POSTGRES, settings);
    }
}
