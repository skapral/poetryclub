package com.github.skapral.poetryclub.db.scalar;

import com.github.skapral.poetryclub.core.scalar.Scalar;
import com.github.skapral.poetryclub.db.access.DbaPoetryClub;

import java.time.LocalDate;
import java.util.UUID;

import static org.jooq.generated.Tables.*;
import static org.jooq.impl.DSL.month;
import static org.jooq.impl.DSL.select;

/**
 * List of unapproved feedbacks, made in certain community this month
 *
 * @author Kapralov Sergey
 */
public class ScalarUnapprovedFeedbacksThisMonth extends ScalarFromJooqRecords {
    /**
     * Ctor.
     * @param communityId Community identity
     */
    public ScalarUnapprovedFeedbacksThisMonth(Scalar<UUID> communityId) {
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
                month(FEEDBACK.TIMESTAMP).eq(LocalDate.now().getMonthValue()),
                FEEDBACK.STATUS.eq("unapproved"),
                CONTRIBUTION.COMMUNITYID.eq(
                    select(COMMUNITY.ID).from(COMMUNITY).where(COMMUNITY.UUID.eq(communityId.value()))
                )
            )

        );
    }
}
