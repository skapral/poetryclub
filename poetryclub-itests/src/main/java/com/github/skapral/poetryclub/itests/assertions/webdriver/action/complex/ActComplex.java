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

package com.github.skapral.poetryclub.itests.assertions.webdriver.action.complex;

import com.github.skapral.poetryclub.itests.assertions.webdriver.action.WebdriverAction;
import io.vavr.collection.List;
import org.openqa.selenium.WebDriver;

/**
 * Complex action, consisting of a sequence of simpler actions
 * 
 * @author skapral
 */
public class ActComplex implements WebdriverAction {
    private final List<WebdriverAction> actions;

    /**
     * Ctor.
     * @param actions Actions
     */
    public ActComplex(List<WebdriverAction> actions) {
        this.actions = actions;
    }

    /**
     * Ctor.
     * @param actions Actions
     */
    public ActComplex(WebdriverAction... actions) {
        this(List.of(actions));
    }

    @Override
    public final void apply(WebDriver source) {
        actions.forEach(a -> a.apply(source));
    }
}
