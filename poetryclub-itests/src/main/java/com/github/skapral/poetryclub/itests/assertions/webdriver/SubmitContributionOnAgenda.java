package com.github.skapral.poetryclub.itests.assertions.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SubmitContributionOnAgenda implements WebdriverAction {
    private final String uri;

    public SubmitContributionOnAgenda(String uri) {
        this.uri = uri;
    }

    @Override
    public final void execute(WebDriver driver) {
        driver.findElement(By.xpath("//form[@action='newcontribution']/input[@name='url']")).sendKeys(uri);
        driver.findElement(By.xpath("//form[@action='newcontribution']")).submit();
    }
}
