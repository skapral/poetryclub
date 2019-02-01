package com.github.skapral.poetryclub.db.config;

import com.github.skapral.config.CpEnvironment;
import com.github.skapral.config.CpOneOf;
import com.github.skapral.config.CpSystemProperty;


/**
 * Config property for JDBC_DATABASE_URL.
 *
 * @author Kapralov Sergey
 */
public class Cp_JDBC_DATABASE_URL extends CpOneOf {
    /**
     * Ctor.
     */
    public Cp_JDBC_DATABASE_URL() {
        super(
            new CpEnvironment("JDBC_DATABASE_URL"),
            new CpSystemProperty("poetryclub.JDBC_DATABASE_URL")
        );
    }
}
