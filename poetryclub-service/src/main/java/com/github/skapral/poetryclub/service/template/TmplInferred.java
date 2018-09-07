package com.github.skapral.poetryclub.service.template;

/**
 * Inferred template
 *
 * @author Kapralov Sergey
 */
public class TmplInferred implements Template {
    private final Inference inference;

    /**
     * Ctor.
     * @param inference Inference
     */
    public TmplInferred(Inference inference) {
        this.inference = inference;
    }

    @Override
    public final String content() {
        return inference.template().content();
    }
}
