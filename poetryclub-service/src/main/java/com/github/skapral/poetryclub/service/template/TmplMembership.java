package com.github.skapral.poetryclub.service.template;

import com.github.skapral.poetryclub.core.scalar.Scalar;
import com.github.skapral.poetryclub.core.scalar.ScalarHash;
import com.github.skapral.poetryclub.db.scalar.ScalarUserMembership;
import io.vavr.Tuple2;

/**
 * Membership page
 *
 * @author Kapralov Sergey
 */
public class TmplMembership extends TmplJtwig2 {
    /**
     * Ctor.
     * @param user User login
     */
    public TmplMembership(Scalar<String> user) {
        super(
            "WEB-INF/templates/membership.twig.html",
            new ScalarHash(
                new Tuple2<>(
                    "membership",
                    new ScalarUserMembership(user)
                ),
                new Tuple2<>(
                    "user", user
                )
            )
        );
    }
}
