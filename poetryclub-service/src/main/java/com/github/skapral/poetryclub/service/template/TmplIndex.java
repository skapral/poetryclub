package com.github.skapral.poetryclub.service.template;

import com.github.skapral.poetryclub.core.config.Cp_OWNER;
import com.github.skapral.poetryclub.core.scalar.Scalar;
import com.github.skapral.poetryclub.core.scalar.ScalarMoreThanZero;
import com.github.skapral.poetryclub.core.scalar.ScalarUserIsServiceOwner;
import com.github.skapral.poetryclub.db.scalar.ScalarUserCommunitiesCount;

/**
 * Index page
 *
 * @author Kapralov Sergey
 */
public class TmplIndex extends TmplConditional {
    /**
     * Ctor.
     * @param user User login
     */
    public TmplIndex(Scalar<String> user) {
        super(
            new ScalarMoreThanZero(
                new ScalarUserCommunitiesCount(
                    user
                )
            ),
            new TmplMembership(user),
            new TmplConditional(
                new ScalarUserIsServiceOwner(
                    new Cp_OWNER(),
                    user
                ),
                new TmplNewCommunity(),
                new TmplJoinCommunity(user)
            )
        );
    }
}
