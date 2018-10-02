package com.github.skapral.poetryclub.db.scalar;

import com.github.skapral.poetryclub.core.scalar.Scalar;
import com.github.skapral.poetryclub.db.access.DbaPoetryClub;

import static com.github.skapral.poetryclub.db.jooq.Tables.*;
import static org.jooq.impl.DSL.coalesce;
import static org.jooq.impl.DSL.select;

/**
 * A table of communities, the user is joined into
 *
 * @author Kapralov Sergey
 */
public class ScalarCommunitiesToJoin extends ScalarFromJooqRecords {
    /**
     * Ctor.
     * @param user The user's login
     */
    public ScalarCommunitiesToJoin(Scalar<String> user) {
        super(
            new DbaPoetryClub(),
            () -> select(
                COMMUNITY.UUID,
                COMMUNITY.NAME,
                coalesce(MEMBER.ROLE, "undefined").as("ROLE")
            ).from(
                COMMUNITY.leftJoin(MEMBER)
                    .on(
                        COMMUNITY.ID.eq(MEMBER.COMMUNITYID),
                        MEMBER.ACCOUNTID.eq(
                            select(ACCOUNT.ID).from(ACCOUNT).where(ACCOUNT.LOGIN.eq(user.value()))
                        )
                    )
            )
        );
    }
}
