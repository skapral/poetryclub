package com.github.skapral.poetryclub.service;

import com.github.skapral.poetryclub.service.config.Cp_TEST_ENV;
import com.github.skapral.poetryclub.service.server.SrvPoetryclub;
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
        new SrvPoetryclub(
            new Cp_TEST_ENV()
        ).start();
        while(true) {
            System.in.read();
        }
    }
}
