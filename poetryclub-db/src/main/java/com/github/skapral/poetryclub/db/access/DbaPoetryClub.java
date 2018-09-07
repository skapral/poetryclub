package com.github.skapral.poetryclub.db.access;

import com.github.skapral.poetryclub.db.config.Cp_JDBC_DATABASE_URL;
import com.github.skapral.poetryclub.db.scalar.datasource.ScalarDatasourceByConfigurableUrl;

/**
 * Poetryclub's database access
 *
 * @author Kapralov Sergey
 */
public class DbaPoetryClub extends DbaDataSource {
    /**
     * Ctor.
     */
    public DbaPoetryClub() {
        super(
            new ScalarDatasourceByConfigurableUrl(
                new Cp_JDBC_DATABASE_URL()
            )
        );
    }
}
