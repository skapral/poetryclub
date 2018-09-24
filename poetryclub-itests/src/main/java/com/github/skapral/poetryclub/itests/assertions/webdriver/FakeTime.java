package com.github.skapral.poetryclub.itests.assertions.webdriver;

import com.github.skapral.poetryclub.core.time.SystimeFaked;
import org.openqa.selenium.WebDriver;

import java.time.LocalDateTime;

/**
 * Sets fake time to certain value
 *
 * @author Kapralov Sergey
 */
public class FakeTime implements WebdriverAction {
    private final LocalDateTime time;

    /**
     * Ctor
     * @param time time
     */
    public FakeTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public final void execute(WebDriver driver) {
        SystimeFaked.Memory.TIME = time;
    }
}
