/*
 * MIT License
 *
 * Copyright (c) 2018 Kapralov Sergey
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 *
 */

package com.github.skapral.poetryclub.itests.assertions;

import com.github.skapral.poetryclub.itests.assertions.flyway.FlywayMigrationScenario;
import com.pragmaticobjects.oo.tests.Assertion;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.postgresql.Driver;
import org.testcontainers.containers.GenericContainer;

/**
 * Assert that certain {@link FlywayMigrationScenario} is applied without failures
 * 
 * @author skapral
 */
public class AssertFlywayMigration implements Assertion {
    private static final GenericContainer PG = new GenericContainer("postgres:10.4")
        .withExposedPorts(5432)
        .withEnv("POSTGRES_PASSWORD", "postgres");

    private final FlywayMigrationScenario scenario;

    /**
     * Ctor.
     * @param scenario Flyway migration scenario
     */
    public AssertFlywayMigration(FlywayMigrationScenario scenario) {
        this.scenario = scenario;
    }

    @Override
    public final void check() throws Exception {
        try {
            PG.start();
            final String jdbcUrl = "jdbc:postgresql://" + PG.getContainerIpAddress() + ":"
                + PG.getMappedPort(5432)
                + "/postgres?user=postgres&password=postgres";
            final HikariConfig config = new HikariConfig();
            config.setJdbcUrl(jdbcUrl);
            config.setDriverClassName(Driver.class.getName());
            final HikariDataSource ds = new HikariDataSource(config);
            scenario.execute(ds);
        } finally {
            PG.stop();
        }
    }
}
