package com.github.skapral.poetryclub.db.scalar;

import com.github.skapral.poetryclub.core.scalar.Scalar;
import com.github.skapral.poetryclub.core.time.SystemTime;
import com.github.skapral.poetryclub.core.time.SystimeAbstractedOutByProperty;
import com.github.skapral.poetryclub.core.time.SystimeReal;
import com.github.skapral.poetryclub.db.access.DatabaseAccess;
import com.github.skapral.poetryclub.db.access.DbaPoetryClub;
import org.jooq.ResultQuery;

import java.time.LocalDate;
import java.time.Year;
import java.util.UUID;
import java.util.function.Supplier;

import static org.jooq.generated.Tables.*;
import static org.jooq.impl.DSL.*;

/**
 * Count of contributions, made by the user in certain community in current month
 *
 * @author Kapralov Sergey
 */
public class ScalarContributionsFeedbackCountByUserThisMonth extends ScalarFromJooqRecords {
    /**
     * Ctor.
     * @param userName User's login
     * @param communityId Community identity
     */
    public ScalarContributionsFeedbackCountByUserThisMonth(Scalar<String> userName, Scalar<UUID> communityId, SystemTime time) {
        super(
            new DbaPoetryClub(),
            () -> select(
                ACCOUNT.LOGIN,
                CONTRIBUTION.URL,
                CONTRIBUTION.UUID,
                coalesce(field("COUNT"), 0).as("COUNT")
            ).from(
                CONTRIBUTION.join(ACCOUNT).on(ACCOUNT.ID.eq(CONTRIBUTION.ACCOUNTID))
                    .leftJoin(
                        select(FEEDBACK.CONTRIBUTIONID.as("CID"), count(FEEDBACK.ID).as("COUNT"))
                            .from(FEEDBACK)
                            .where(
                                FEEDBACK.ACCOUNTID.eq(
                                    select(ACCOUNT.ID).from(ACCOUNT).where(ACCOUNT.LOGIN.eq(userName.value()))
                                )
                            )
                            .groupBy(FEEDBACK.CONTRIBUTIONID)
                ).on(
                    CONTRIBUTION.ID.eq(field(name("CID"), Long.class))
                )
            ).where(
                year(CONTRIBUTION.TIMESTAMP).eq(Year.from(time.time()).getValue()),
                month(CONTRIBUTION.TIMESTAMP).eq(LocalDate.from(time.time()).getMonthValue()),
                CONTRIBUTION.ACCOUNTID.ne(
                    select(ACCOUNT.ID).from(ACCOUNT).where(ACCOUNT.LOGIN.eq(userName.value()))
                ),
                CONTRIBUTION.COMMUNITYID.eq(
                    select(COMMUNITY.ID).from(COMMUNITY).where(COMMUNITY.UUID.eq(communityId.value()))
                )
            )
        );
    }

    public ScalarContributionsFeedbackCountByUserThisMonth(Scalar<String> userName, Scalar<UUID> communityId) {
        this(
            userName,
            communityId,
            new SystimeAbstractedOutByProperty()
        );
    }
}
