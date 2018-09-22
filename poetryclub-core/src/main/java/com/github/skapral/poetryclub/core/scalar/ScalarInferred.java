package com.github.skapral.poetryclub.core.scalar;

public class ScalarInferred<T> implements Scalar<T> {
    private final Inference<T> inference;

    public ScalarInferred(Inference<T> inference) {
        this.inference = inference;
    }

    @Override
    public final T value() {
        return inference.scalar().value();
    }
}
