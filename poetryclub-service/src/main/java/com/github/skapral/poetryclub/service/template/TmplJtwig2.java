package com.github.skapral.poetryclub.service.template;

import com.github.skapral.poetryclub.core.scalar.Scalar;
import com.github.skapral.poetryclub.core.scalar.ScalarStatic;
import io.vavr.collection.HashMap;

import java.util.Map;

/**
 * Special kind of {@link TmplJtwig}, which holds the model as a set of scalars
 *
 * @author Kapralov Sergey
 */
public class TmplJtwig2 extends TmplInferred {
    /**
     * Ctor.
     * @param path Template path
     * @param model model
     */
    public TmplJtwig2(String path, Scalar<Map<String, Scalar>> model) {
        super(
            new Inference(
                path,
                model
            )
        );
    }

    /**
     * Ctor.
     * @param path Template path
     * @param model model
     */
    public TmplJtwig2(String path, Map<String, Scalar> model) {
        this(path, new ScalarStatic<>(model));
    }

    /**
     * {@link TmplJtwig2} inference
     * @author Kapralov Sergey
     */
    private static class Inference implements Template.Inference {
        private final String path;
        private final Scalar<Map<String, Scalar>> model;

        /**
         * Ctor.
         * @param path Template path
         * @param model model
         */
        public Inference(String path, Scalar<Map<String, Scalar>> model) {
            this.path = path;
            this.model = model;
        }

        @Override
        public final Template template() {
            return new TmplJtwig(
                path,
                HashMap.ofAll(model.value()).mapValues(Scalar::value)
                    .toJavaMap()
            );
        }
    }
}
