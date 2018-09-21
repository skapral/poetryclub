package com.github.skapral.poetryclub.core.time;

import java.time.LocalDateTime;

public class SystimeInferred implements SystemTime {
    private final Inference inference;

    public SystimeInferred(Inference inference) {
        this.inference = inference;
    }

    @Override
    public final LocalDateTime time() {
        return inference.systemTime().time();
    }
}
