package com.github.skapral.poetryclub.itests.assertions.webdriver;


import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AssertSummaryPage implements WebdriverAction {
    private final String communityName;

    public AssertSummaryPage(String communityName) {
        this.communityName = communityName;
    }

    @Override
    public final void execute(WebDriver driver) {
        Assertions.assertThat(driver.findElement(By.tagName("h1")).getText())
                .isEqualTo("Summary for " + communityName);
    }
}
