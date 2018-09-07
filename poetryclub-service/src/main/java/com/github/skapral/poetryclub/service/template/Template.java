package com.github.skapral.poetryclub.service.template;

/**
 * Template
 */
public interface Template {
    /**
     * @return resolved contents
     */
    String content();

    /**
     * Template's inference
     */
    interface Inference {
        /**
         * @return Inferred template
         */
        Template template();
    }
}
