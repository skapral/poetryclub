package com.github.skapral.poetryclub.service;

import com.github.skapral.poetryclub.service.config.Cp_PORT;
import com.github.skapral.poetryclub.service.config.Cp_TEST_ENV;
import com.github.skapral.poetryclub.service.scalar.ScalarPoetryClubJerseyResourceConfig;
import com.github.skapral.poetryclub.service.server.SrvGrizzlyWithJerseyAndJtwig;
import com.pragmaticobjects.oo.atom.anno.NotAtom;

/**
 * Entry point of Poetryclub web service.
 *
 * @author Kapralov Sergey
 */
@NotAtom
public class Main {
    /**
     * Main
     * @param args Command line args
     * @throws Exception if something goes wrong
     */
    public static void main(String... args) throws Exception {
        new SrvGrizzlyWithJerseyAndJtwig(
            new Cp_PORT(),
            new ScalarPoetryClubJerseyResourceConfig(
                new Cp_TEST_ENV()
            )
        ).start();
        while(true) {
            System.in.read();
        }
    }
}
