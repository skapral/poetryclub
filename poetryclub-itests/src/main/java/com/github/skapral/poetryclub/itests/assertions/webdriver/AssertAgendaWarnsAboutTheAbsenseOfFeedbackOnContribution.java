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

package com.github.skapral.poetryclub.itests.assertions.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Asserts that agenda has a warning message to the currently logged in user that he/she haven't made any feedback to
 * certain contribution
 */
public class AssertAgendaWarnsAboutTheAbsenseOfFeedbackOnContribution implements WebdriverAction {
    private final String contributionUrl;

    /**
     * Ctor.
     * @param contributionUrl Contribution URL
     */
    public AssertAgendaWarnsAboutTheAbsenseOfFeedbackOnContribution(String contributionUrl) {
        this.contributionUrl = contributionUrl;
    }

    @Override
    public final void execute(WebDriver driver) {
        boolean warning = driver.findElements(
            By.tagName("div")
        ).stream().map(e -> e.getText())
            .anyMatch(str -> str.contains(
                String.format(
                    "You haven't left any feedback to contribution %s last month.",
                    contributionUrl
                )
            ));
        assertThat(warning).isTrue();
    }
}
