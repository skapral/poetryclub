package com.github.skapral.poetryclub.itests.assertions.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class AssertSummaryPageDisplaysContribution implements WebdriverAction {
    private final String contributionUrl;
    private final String contributorName;

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
