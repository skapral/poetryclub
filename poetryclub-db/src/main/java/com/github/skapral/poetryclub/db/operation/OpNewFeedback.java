package com.github.skapral.poetryclub.db.operation;

import com.github.skapral.poetryclub.core.scalar.Scalar;
import com.github.skapral.poetryclub.db.access.DbaPoetryClub;
import org.jooq.generated.Tables;

import java.net.URL;
import java.util.UUID;

import static org.jooq.generated.Tables.FEEDBACK;
import static org.jooq.impl.DSL.*;

/**
 * Operation, creating new feedback.
 *
 * @author Kapralov Sergey
 */
public class OpNewFeedback extends OpJooq {
    /**
     * Ctor.
     * @param user User login
     * @param contribution Contribution identity
     * @param url Feedback's URL
     */
    public OpNewFeedback(Scalar<String> user, Scalar<UUID> contribution, Scalar<URL> url) {
        super(
            new DbaPoetryClub(),
            () -> insertInto(
                FEEDBACK,
                FEEDBACK.UUID,
                FEEDBACK.ACCOUNTID,
                FEEDBACK.CONTRIBUTIONID,
                FEEDBACK.URL
            ).values(
                val(UUID.randomUUID()),
                select(Tables.ACCOUNT.ID).from(Tables.ACCOUNT).where(Tables.ACCOUNT.LOGIN.eq(user.value())).asField(),
                select(Tables.CONTRIBUTION.ID).from(Tables.CONTRIBUTION).where(Tables.CONTRIBUTION.UUID.eq(contribution.value())).asField(),
                val(url.value().toString())
            )
        );
    }
}
