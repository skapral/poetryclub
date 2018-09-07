package com.github.skapral.poetryclub.db.scalar;

import com.github.skapral.poetryclub.core.scalar.Scalar;
import com.github.skapral.poetryclub.db.access.DbaPoetryClub;

import java.time.LocalDate;
import java.time.Year;
import java.util.UUID;

import static org.jooq.generated.Tables.*;
import static org.jooq.impl.DSL.*;

/**
 * List of unapproved contributions, made in certain community this month
 *
 * @author Kapralov Sergey
 */
public class ScalarUnapprovedContributionsThisMonth extends ScalarFromJooqRecords {
    /**
     * Ctor.
     * @param communityId Community identity
     */
    public ScalarUnapprovedContributionsThisMonth(Scalar<UUID> communityId) {
        super(
            new DbaPoetryClub(),
            () -> select(
                ACCOUNT.LOGIN,
                CONTRIBUTION.UUID,
                CONTRIBUTION.URL
            ).from(
                CONTRIBUTION.join(ACCOUNT).on(ACCOUNT.ID.eq(CONTRIBUTION.ACCOUNTID))
            ).where(
                year(CONTRIBUTION.TIMESTAMP).eq(Year.now().getValue()),
                month(CONTRIBUTION.TIMESTAMP).eq(LocalDate.now().getMonthValue()),
                CONTRIBUTION.STATUS.eq("unapproved"),
                CONTRIBUTION.COMMUNITYID.eq(
                    select(COMMUNITY.ID).from(COMMUNITY).where(COMMUNITY.UUID.eq(communityId.value()))
                )
            )
        );
    }
}
