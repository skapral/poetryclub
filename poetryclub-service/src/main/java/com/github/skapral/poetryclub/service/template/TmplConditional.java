package com.github.skapral.poetryclub.service.template;

import com.github.skapral.poetryclub.core.scalar.Scalar;

/**
 * Template, contents of which are defined by condition result
 *
 * @author Kapralov Sergey
 */
public class TmplConditional implements Template {
    private final Scalar<Boolean> condition;
    private final Template positiveTemplate;
    private final Template negativeTemplate;

    /**
     * Ctor.
     * @param condition Condition
     * @param positiveTemplate Template for condition is true
     * @param negativeTemplate Template for condition is false
     */
    public TmplConditional(Scalar<Boolean> condition, Template positiveTemplate, Template negativeTemplate) {
        this.condition = condition;
        this.positiveTemplate = positiveTemplate;
        this.negativeTemplate = negativeTemplate;
    }

    @Override
    public final String content() {
        if(condition.value()) {
            return positiveTemplate.content();
        } else {
            return negativeTemplate.content();
        }
    }
}
