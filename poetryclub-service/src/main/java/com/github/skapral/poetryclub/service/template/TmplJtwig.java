package com.github.skapral.poetryclub.service.template;

import com.github.skapral.poetryclub.core.scalar.Scalar;
import com.github.skapral.poetryclub.core.scalar.ScalarStatic;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.util.Map;

/**
 * JTwig template
 *
 * @author Kapralov Sergey
 */
public class TmplJtwig implements Template {
    private final String path;
    private final Scalar<Map<String, Object>> model;

    /**
     * Ctor
     * @param path View path
     * @param model Model source
     */
    public TmplJtwig(String path, Scalar<Map<String, Object>> model) {
        this.path = path;
        this.model = model;
    }

    /**
     * Ctor
     * @param path View path
     * @param model Model source
     */
    public TmplJtwig(String path, Map<String, Object> model) {
        this(path, new ScalarStatic<>(model));
    }

    @Override
    public final String content() {
        final JtwigTemplate template = JtwigTemplate.classpathTemplate(path);
        final JtwigModel model = JtwigModel.newModel(this.model.value());
        return template.render(model);
    }
}
