package com.github.skapral.poetryclub.db.scalar;

import com.github.skapral.poetryclub.core.scalar.Scalar;
import com.github.skapral.poetryclub.db.access.DbaPoetryClub;
import org.jooq.generated.tables.Community;

import java.util.UUID;

import static org.jooq.generated.tables.Account.ACCOUNT;
import static org.jooq.generated.tables.Contribution.CONTRIBUTION;
import static org.jooq.impl.DSL.select;

public class ScalarCommunityContributions extends ScalarFromJooqRecords {
    public ScalarCommunityContributions(Scalar<UUID> communityId) {
        super(
            new DbaPoetryClub(),
            () -> select(ACCOUNT.LOGIN, CONTRIBUTION.URL, CONTRIBUTION.TIMESTAMP)
                .from(CONTRIBUTION.join(ACCOUNT).on(ACCOUNT.ID.eq(CONTRIBUTION.ACCOUNTID)))
                .where(CONTRIBUTION.COMMUNITYID.eq(
                    select(Community.COMMUNITY.ID)
                        .from(Community.COMMUNITY)
                        .where(Community.COMMUNITY.UUID.eq(communityId.value()))
                ))
                .orderBy(CONTRIBUTION.TIMESTAMP.desc())

        );
    }
}
