package com.github.skapral.poetryclub.core.time;

import java.time.LocalDateTime;

public interface SystemTime {
    LocalDateTime time();

    interface Inference {
        SystemTime systemTime();
    }
}
