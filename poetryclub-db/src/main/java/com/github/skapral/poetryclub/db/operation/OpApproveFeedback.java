package com.github.skapral.poetryclub.db.operation;

import com.github.skapral.poetryclub.core.scalar.Scalar;
import com.github.skapral.poetryclub.db.access.DbaPoetryClub;

import java.util.UUID;

import static com.github.skapral.poetryclub.db.jooq.Tables.FEEDBACK;
import static org.jooq.impl.DSL.update;

/**
 * Feedback approval
 *
 * @author Kapralov Sergey
 */
public class OpApproveFeedback extends OpJooq {
    /**
     * Ctor.
     * @param feedbackId Feedback identity
     */
    public OpApproveFeedback(Scalar<UUID> feedbackId) {
        super(
            new DbaPoetryClub(),
            () -> update(FEEDBACK).set(FEEDBACK.STATUS, "approved")
                .where(FEEDBACK.UUID.eq(feedbackId.value()))
        );
    }
}
