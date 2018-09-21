package com.github.skapral.poetryclub.db.scalar;

import com.github.skapral.poetryclub.core.scalar.Scalar;
import com.github.skapral.poetryclub.db.access.DbaPoetryClub;

import java.util.UUID;

import static org.jooq.generated.Tables.*;
import static org.jooq.impl.DSL.select;

public class ScalarCommunityMembers extends ScalarFromJooqRecords {
    public ScalarCommunityMembers(Scalar<UUID> community) {
        super(
            new DbaPoetryClub(),
            () -> select(ACCOUNT.LOGIN, MEMBER.ROLE)
                .from(MEMBER.join(ACCOUNT).on(ACCOUNT.ID.eq(MEMBER.ACCOUNTID)))
                .where(MEMBER.COMMUNITYID.eq(
                    select(COMMUNITY.ID).from(COMMUNITY).where(COMMUNITY.UUID.eq(community.value()))
                ))
        );
    }
}
