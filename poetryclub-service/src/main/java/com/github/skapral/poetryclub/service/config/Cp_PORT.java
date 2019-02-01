package com.github.skapral.poetryclub.service.config;

import com.github.skapral.config.CpEnvironment;
import com.github.skapral.config.CpOneOf;
import com.github.skapral.config.CpStatic;

/**
 * PORT property
 * @author Kapralov Sergey
 */
public class Cp_PORT extends CpOneOf {
    /**
     * Ctor.
     */
    public Cp_PORT() {
        super(
            new CpEnvironment("PORT"),
            new CpStatic("5000")
        );
    }
}
