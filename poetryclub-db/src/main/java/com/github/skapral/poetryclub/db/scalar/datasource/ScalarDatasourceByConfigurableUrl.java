package com.github.skapral.poetryclub.db.scalar.datasource;

import com.github.skapral.poetryclub.core.config.ConfigProperty;
import com.github.skapral.poetryclub.core.scalar.ScalarMemoizedValue;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;
import org.postgresql.Driver;

import javax.sql.DataSource;

/**
 * Datasource, provided by JDBC URL. JDBC URL is provided from configuration property
 *
 * @author Kapralov Sergey
 */
public class ScalarDatasourceByConfigurableUrl extends ScalarMemoizedValue<DataSource> {
    /**
     * Ctor.
     * @param cacheKey Cache key
     * @param jdbcUrl JDBC URL
     */
    public ScalarDatasourceByConfigurableUrl(String cacheKey, ConfigProperty jdbcUrl) {
        super(
            cacheKey,
            () -> {
                final HikariConfig config = new HikariConfig();
                config.setJdbcUrl(jdbcUrl.optionalValue().get());
                config.setDriverClassName(Driver.class.getName());
                final HikariDataSource ds = new HikariDataSource(config);
                Flyway flyway = new Flyway();
                flyway.setDataSource(ds);
                flyway.migrate();
                return ds;
            }
        );
    }

    /**
     * Ctor.
     * @param jdbcUrl JDBC URL
     */
    public ScalarDatasourceByConfigurableUrl(ConfigProperty jdbcUrl) {
        this("dataSource", jdbcUrl);
    }
}
