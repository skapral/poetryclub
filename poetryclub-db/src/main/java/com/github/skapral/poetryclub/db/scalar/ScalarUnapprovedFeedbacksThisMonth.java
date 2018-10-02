package com.github.skapral.poetryclub.db.scalar;

import com.github.skapral.poetryclub.core.scalar.Scalar;
import com.github.skapral.poetryclub.core.time.SystemTime;
import com.github.skapral.poetryclub.core.time.SystimeAbstractedOutByProperty;
import com.github.skapral.poetryclub.db.access.DbaPoetryClub;

import java.time.LocalDate;
import java.time.Year;
import java.util.UUID;

import static com.github.skapral.poetryclub.db.jooq.Tables.*;
import static org.jooq.impl.DSL.month;
import static org.jooq.impl.DSL.select;
import static org.jooq.impl.DSL.year;

/**
 * List of unapproved feedbacks, made in certain community this month
 *
 * @author Kapralov Sergey
 */
public class ScalarUnapprovedFeedbacksThisMonth extends ScalarFromJooqRecords {
    /**
     * Ctor.
     * @param communityId Community identity
     * @param time System time
     */
    public ScalarUnapprovedFeedbacksThisMonth(Scalar<UUID> communityId, SystemTime time) {
        super(
            new DbaPoetryClub(),
            () -> select(
                ACCOUNT.LOGIN,
                CONTRIBUTION.UUID.as("CONTRIBUTION_UUID"),
                CONTRIBUTION.URL.as("CONTRIBUTION_URL"),
                FEEDBACK.UUID.as("FEEDBACK_UUID"),
                FEEDBACK.URL.as("FEEDBACK_URL")
            ).from(
                FEEDBACK
                    .join(CONTRIBUTION).on(CONTRIBUTION.ID.eq(FEEDBACK.CONTRIBUTIONID))
                    .join(ACCOUNT).on(ACCOUNT.ID.eq(FEEDBACK.ACCOUNTID))
            )
            .where(
                    year(CONTRIBUTION.TIMESTAMP).eq(Year.from(time.time()).getValue()),
                    month(CONTRIBUTION.TIMESTAMP).eq(LocalDate.from(time.time()).getMonthValue()),
                FEEDBACK.STATUS.eq("unapproved"),
                CONTRIBUTION.COMMUNITYID.eq(
                    select(COMMUNITY.ID).from(COMMUNITY).where(COMMUNITY.UUID.eq(communityId.value()))
                )
            )

        );
    }

    /**
     * Ctor.
     * @param communityId Community identity
     */
    public ScalarUnapprovedFeedbacksThisMonth(Scalar<UUID> communityId) {
        this(communityId, new SystimeAbstractedOutByProperty());
    }
}
