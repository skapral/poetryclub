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

package com.github.skapral.poetryclub.itests.assertions.webdriver.action;

import com.github.skapral.poetryclub.core.time.SystimeFaked;
import org.openqa.selenium.WebDriver;

import java.time.Duration;


/**
 * Simulates waiting for certain amount of time, by shifting fake time to some 
 * duration
 * 
 * @author skapral
 */
public class ActWait implements WebdriverAction {
    private final Duration duration;

    /**
     * Ctor.
     * @param duration Duration
     */
    public ActWait(Duration duration) {
        this.duration = duration;
    }

    @Override
    public final void apply(WebDriver source) {
        SystimeFaked.Memory.TIME = SystimeFaked.Memory.TIME.plus(duration);
    }
}
