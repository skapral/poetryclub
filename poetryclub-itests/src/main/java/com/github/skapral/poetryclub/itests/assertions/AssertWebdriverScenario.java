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

import com.github.skapral.poetryclub.itests.assertions.webdriver.action.WebdriverAction;
import com.pragmaticobjects.oo.tests.Assertion;
import io.vavr.collection.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

/**
 * Checks that certain sequence of actions on WebDriver passes
 *
 * @author Kapralov Sergey
 */
public class AssertWebdriverScenario implements Assertion {
    private final List<WebdriverAction> actions;

    /**
     * Ctor.
     * @param actions Web driver actions
     */
    public AssertWebdriverScenario(List<WebdriverAction> actions) {
        this.actions = actions;
    }

    /**
     * Ctor.
     * @param actions Web driver actions
     */
    public AssertWebdriverScenario(WebdriverAction... actions) {
        this(List.of(actions));
    }

    @Override
    public final void check() throws Exception {
        final ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--disable-gpu");
        final DesiredCapabilities dc = new DesiredCapabilities();
        dc.setJavascriptEnabled(true);
        dc.setCapability(
            ChromeOptions.CAPABILITY, chromeOptions
        );
        WebDriver driver = new ChromeDriver(dc);
        try {
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            actions.forEach(a -> a.apply(driver));
        } catch(Throwable t) {
            throw new AssertionError(
                String.join(
                    "\r\n",
                    "Webdriver scenario failed",
                    "CURRENT_URL = " + driver.getCurrentUrl(),
                    "====================================================",
                    driver.getPageSource()
                ),
                t
            );
        } finally {
            driver.quit();
        }
    }
}
