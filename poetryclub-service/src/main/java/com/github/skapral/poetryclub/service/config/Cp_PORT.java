package com.github.skapral.poetryclub.service.config;

import com.github.skapral.poetryclub.core.config.CpEnvironment;
import com.github.skapral.poetryclub.core.config.CpOneOf;
import com.github.skapral.poetryclub.core.config.CpStatic;

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
