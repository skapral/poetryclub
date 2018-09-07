package com.github.skapral.poetryclub.db.scalar;

import com.github.skapral.poetryclub.core.scalar.Scalar;
import com.github.skapral.poetryclub.db.access.DbaPoetryClub;

import java.time.LocalDate;
import java.time.Year;
import java.util.UUID;

import static org.jooq.generated.Tables.*;
import static org.jooq.impl.DSL.*;

/**
 * List of user contributions, made in this month
 *
 * @author Kapralov Sergey
 */
public class ScalarUserContributionsThisMonth extends ScalarFromJooqRecords {
    /**
     * Ctor.
     * @param userName User login
     * @param community Community identity
     */
    public ScalarUserContributionsThisMonth(Scalar<String> userName, Scalar<UUID> community) {
        super(
            new DbaPoetryClub(),
            () -> select(
                CONTRIBUTION.URL
            ).from(
                CONTRIBUTION
            ).where(
                year(CONTRIBUTION.TIMESTAMP).eq(Year.now().getValue()),
                month(CONTRIBUTION.TIMESTAMP).eq(LocalDate.now().getMonthValue()),
                CONTRIBUTION.COMMUNITYID.eq(
                    select(COMMUNITY.ID).from(COMMUNITY).where(COMMUNITY.UUID.eq(community.value()))
                ),
                CONTRIBUTION.ACCOUNTID.eq(
                    select(ACCOUNT.ID).from(ACCOUNT).where(ACCOUNT.LOGIN.eq(userName.value()))
                )
            )
        );
    }
}
