package com.github.skapral.poetryclub.db.scalar;

import com.github.skapral.poetryclub.core.scalar.Scalar;
import com.github.skapral.poetryclub.db.access.DbaPoetryClub;

import static org.jooq.generated.Tables.*;
import static org.jooq.impl.DSL.count;
import static org.jooq.impl.DSL.select;

/**
 * Count of communities which user is joined into
 *
 * @author Kapralov Sergey
 */
public class ScalarUserCommunitiesCount extends ScalarFromJooqValue<Integer> {
    /**
     * Ctor.
     * @param userName User login
     */
    public ScalarUserCommunitiesCount(Scalar<String> userName) {
        super(
            new DbaPoetryClub(),
            () -> select(
                count()
            ).from(COMMUNITY.innerJoin(MEMBER).on(COMMUNITY.ID.eq(MEMBER.COMMUNITYID)))
                .where(
                    MEMBER.ACCOUNTID.eq(
                        select(ACCOUNT.ID).from(ACCOUNT).where(ACCOUNT.LOGIN.eq(userName.value()))
                    )
                )
        );
    }
}
