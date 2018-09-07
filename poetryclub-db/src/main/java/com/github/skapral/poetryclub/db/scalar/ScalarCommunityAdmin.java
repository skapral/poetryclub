package com.github.skapral.poetryclub.db.scalar;

import com.github.skapral.poetryclub.core.scalar.Scalar;
import com.github.skapral.poetryclub.db.access.DbaPoetryClub;

import java.util.UUID;

import static org.jooq.generated.Tables.*;
import static org.jooq.impl.DSL.select;

/**
 * The admin of community
 *
 * @author Kapralov Sergey
 */
public class ScalarCommunityAdmin extends ScalarFromJooqValue<String> {
    /**
     * Ctor.
     * @param communityId Community identity
     */
    public ScalarCommunityAdmin(Scalar<UUID> communityId) {
        super(
            new DbaPoetryClub(),
            () -> select(ACCOUNT.LOGIN)
                .from(ACCOUNT.join(MEMBER).on(ACCOUNT.ID.eq(MEMBER.ACCOUNTID)))
                .where(
                    MEMBER.COMMUNITYID.eq(
                        select(COMMUNITY.ID).from(COMMUNITY).where(COMMUNITY.UUID.eq(communityId.value()))
                    ),
                    MEMBER.ROLE.eq("admin")
                )
        );
    }
}
