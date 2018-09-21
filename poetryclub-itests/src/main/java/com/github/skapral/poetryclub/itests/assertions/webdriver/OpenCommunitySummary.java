package com.github.skapral.poetryclub.itests.assertions.webdriver;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.*;

public class OpenCommunitySummary implements WebdriverAction {
    @Override
    public final void execute(WebDriver driver) {
        driver.findElement(By.linkText("Overall summary")).click();
    }
}
