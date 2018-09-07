package com.github.skapral.poetryclub.service.template;

import com.github.skapral.poetryclub.core.scalar.Scalar;
import com.github.skapral.poetryclub.core.scalar.ScalarHash;
import com.github.skapral.poetryclub.db.scalar.ScalarCommunitiesToJoin;
import io.vavr.Tuple2;

/**
 * "Join community" view
 *
 * @author Kapralov Sergey
 */
public class TmplJoinCommunity extends TmplJtwig2 {
    /**
     * Ctor.
     * @param user User login
     */
    public TmplJoinCommunity(Scalar<String> user) {
        super(
            "WEB-INF/templates/joincommunity.twig.html",
            new ScalarHash(
                new Tuple2(
                    "communities", new ScalarCommunitiesToJoin(
                        user
                    )
                )
            )
        );
    }
}
