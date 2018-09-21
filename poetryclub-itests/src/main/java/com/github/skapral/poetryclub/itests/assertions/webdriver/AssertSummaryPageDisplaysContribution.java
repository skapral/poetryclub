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
import org.openqa.selenium.WebElement;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Asserts that the summary page mentions certain contribution
 *
 * @author Kapralov Sergey
 */
public class AssertSummaryPageDisplaysContribution implements WebdriverAction {
    private final String contributionUrl;
    private final String contributorName;

    /**
     * Ctor.
     * @param contributionUrl Contribution URL
     * @param contributorName Contribution name
     */
    public AssertSummaryPageDisplaysContribution(String contributionUrl, String contributorName) {
        this.contributionUrl = contributionUrl;
        this.contributorName = contributorName;
    }

    @Override
    public final void execute(WebDriver driver) {
        Optional<WebElement> membership = driver
                .findElement(By.id("contributions"))
                .findElements(By.xpath("tbody/tr"))
                .stream()
                .filter(element ->
                        element.findElement(By.xpath("td[2]")).getText().equals(contributionUrl)
                )
                .findAny();
        assertThat(membership).withFailMessage("Contribution " + contributionUrl + " is missing in summary table").isNotEmpty();
        assertThat(membership.get().findElement(By.xpath("td[3]")).getText()).isEqualTo(contributorName);
    }
}
