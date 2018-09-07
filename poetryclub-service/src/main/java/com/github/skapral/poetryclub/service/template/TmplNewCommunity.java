package com.github.skapral.poetryclub.service.template;

import com.github.skapral.poetryclub.core.scalar.ScalarHash;

/**
 * New community page
 *
 * @author Kapralov Sergey
 */
public class TmplNewCommunity extends TmplJtwig2 {
    /**
     * Ctor.
     */
    public TmplNewCommunity() {
        super(
            "WEB-INF/templates/newcommunity.twig.html",
            new ScalarHash()
        );
    }
}
