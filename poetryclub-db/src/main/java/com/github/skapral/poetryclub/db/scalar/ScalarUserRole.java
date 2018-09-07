package com.github.skapral.poetryclub.db.scalar;

import com.github.skapral.poetryclub.core.scalar.Scalar;
import com.github.skapral.poetryclub.db.access.DbaPoetryClub;

import java.util.UUID;

import static org.jooq.generated.Tables.*;
import static org.jooq.impl.DSL.select;

/**
 * The role of the user in certain community
 *
 * @author Kapralov Sergey
 */
public class ScalarUserRole extends ScalarFromJooqValue<String> {
    /**
     * Ctor.
     * @param communityId Community identity
     * @param userLogin User login
     */
    public ScalarUserRole(Scalar<UUID> communityId, Scalar<String> userLogin) {
        super(
            new DbaPoetryClub(),
            () -> select(MEMBER.ROLE)
                .from(MEMBER)
                .where(
                    MEMBER.COMMUNITYID.eq(
                        select(COMMUNITY.ID).from(COMMUNITY).where(COMMUNITY.UUID.eq(communityId.value()))
                    ),
                    MEMBER.ACCOUNTID.eq(
                        select(ACCOUNT.ID).from(ACCOUNT).where(ACCOUNT.LOGIN.eq(userLogin.value()))
                    )
                )
        );
    }
}
