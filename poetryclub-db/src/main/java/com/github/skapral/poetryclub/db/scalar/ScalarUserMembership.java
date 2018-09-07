package com.github.skapral.poetryclub.db.scalar;

import com.github.skapral.poetryclub.core.scalar.Scalar;
import com.github.skapral.poetryclub.db.access.DbaPoetryClub;

import static org.jooq.generated.Tables.*;
import static org.jooq.impl.DSL.select;

/**
 * List of of all memberships of the user
 *
 * @author Kapralov Sergey
 */
public class ScalarUserMembership extends ScalarFromJooqRecords {
    /**
     * Ctor.
     * @param userName User's login
     */
    public ScalarUserMembership(Scalar<String> userName) {
        super(
            new DbaPoetryClub(),
            () -> select(
                    COMMUNITY.UUID,
                    COMMUNITY.NAME,
                    MEMBER.ROLE
                )
                .from(
                    MEMBER.join(COMMUNITY)
                        .on(MEMBER.ACCOUNTID.eq(
                            select(ACCOUNT.ID).from(ACCOUNT)
                                .where(ACCOUNT.LOGIN.eq(userName.value()))
                        ))
                )
        );
    }
}
