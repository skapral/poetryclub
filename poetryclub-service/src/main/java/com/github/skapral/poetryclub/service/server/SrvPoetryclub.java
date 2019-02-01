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

package com.github.skapral.poetryclub.service.server;

import com.github.skapral.config.ConfigProperty;
import com.github.skapral.jersey.se.Server;
import com.github.skapral.jersey.se.SrvGrizzlyWithJersey;
import com.github.skapral.poetryclub.service.config.Cp_PORT;
import com.github.skapral.poetryclub.service.jersey.PoetryClubFakedAuthenticationAPI;
import com.github.skapral.poetryclub.service.jersey.PoetryClubAPI;

public class SrvPoetryclub implements Server {
    private final ConfigProperty configProperty;

    public SrvPoetryclub(ConfigProperty configProperty) {
        this.configProperty = configProperty;
    }

    @Override
    public final ServerStopHandle start() {
        if(configProperty.optionalValue().isDefined()) {
            return new SrvGrizzlyWithJersey(
                new Cp_PORT(),
                new PoetryClubFakedAuthenticationAPI()
            ).start();
        } else {
            return new SrvGrizzlyWithJersey(
                new Cp_PORT(),
                new PoetryClubAPI()
            ).start();
        }
    }
}
