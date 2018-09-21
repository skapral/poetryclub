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

import com.github.skapral.poetryclub.core.scalar.ScalarStatic;
import com.github.skapral.poetryclub.service.config.Cp_PORT;
import com.github.skapral.poetryclub.service.jersey.PoetryClubFakedAuthenticationAPI;
import com.github.skapral.poetryclub.service.server.Server;
import com.github.skapral.poetryclub.service.server.SrvGrizzlyWithJerseyAndJtwig;
import com.pragmaticobjects.oo.tests.Assertion;
import org.testcontainers.containers.GenericContainer;

import java.util.function.Supplier;

/**
 * Check assertion while there is test poetryclub instance online
 *
 * @author Kapralov Sergey
 */
public class AssertAssumingPoetryclubInstance implements Assertion {
    private static final GenericContainer PG = new GenericContainer("postgres:10.4")
        .withExposedPorts(5432)
        .withEnv("POSTGRES_PASSWORD", "postgres");

    private static final Supplier<Server> poetryServer = () -> new SrvGrizzlyWithJerseyAndJtwig(
        new Cp_PORT(),
        new ScalarStatic<>(
            new PoetryClubFakedAuthenticationAPI()
        )
    );

    private final Assertion assertion;

    /**
     * Ctor.
     * @param assertion Assertion to check
     */
    public AssertAssumingPoetryclubInstance(Assertion assertion) {
        this.assertion = assertion;
    }

    @Override
    public final void check() throws Exception {
        try {
            PG.start();
            String jdbcUrl = "jdbc:postgresql://" + PG.getContainerIpAddress() + ":"
                + PG.getMappedPort(5432)
                + "/postgres?user=postgres&password=postgres";
            System.setProperty("poetryclub.JDBC_DATABASE_URL", jdbcUrl);
            System.setProperty("poetryclub.OWNER", "owner");
            System.setProperty("poetryclub.FAKE_SYSTIME", "true");
            Server.ServerStopHandle stopHandle = poetryServer.get().start();
            try {
                assertion.check();
            } finally {
                stopHandle.stop();
            }
        } finally {
            PG.stop();
        }
    }
}
