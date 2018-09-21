package com.github.skapral.poetryclub.itests.assertions.webdriver;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class AssertSummaryPageDisplaysMember implements WebdriverAction {
    private final String memberName;
    private final String memberRole;

    public AssertSummaryPageDisplaysMember(String memberName, String memberRole) {
        this.memberName = memberName;
        this.memberRole = memberRole;
    }

    @Override
    public final void execute(WebDriver driver) {
        Optional<WebElement> membership = driver
                .findElement(By.id("membership"))
                .findElements(By.xpath("tbody/tr"))
                .stream()
                .filter(element ->
                        element.findElement(By.xpath("td[1]")).getText().equals(memberName)
                )
                .findAny();
        assertThat(membership).withFailMessage("Membership for " + memberName + " is missing in summary table").isNotEmpty();
        assertThat(membership.get().findElement(By.xpath("td[2]")).getText()).isEqualTo(memberRole);
    }
}
