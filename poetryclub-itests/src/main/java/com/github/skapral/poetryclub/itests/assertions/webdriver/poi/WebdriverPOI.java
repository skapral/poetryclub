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

package com.github.skapral.poetryclub.itests.assertions.webdriver.poi;

import io.vavr.control.Option;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Web Driver's Point of interest - certain element on page, which has some importance
 * to the test scenarios. May be absent.
 * 
 * @author skapral
 */
public interface WebdriverPOI {
    /**
     * @param source Web driver, to where the POI is searched
     * @return web element, if found
     */
    Option<WebElement> webElement(WebDriver source);

    /**
     * {@link WebdriverPOI} inference.
     * @author skapral
     */
    @FunctionalInterface
    interface Inference {
        /**
         * @return Inferred poi
         */
        WebdriverPOI poi();
    }
}
