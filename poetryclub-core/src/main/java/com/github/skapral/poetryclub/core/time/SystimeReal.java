package com.github.skapral.poetryclub.core.time;

import java.time.LocalDateTime;

public class SystimeReal implements SystemTime {
    @Override
    public final LocalDateTime time() {
        return LocalDateTime.now();
    }
}
