package com.github.skapral.poetryclub.db.operation;

import com.github.skapral.poetryclub.core.scalar.Scalar;
import com.github.skapral.poetryclub.db.access.DbaPoetryClub;

import java.util.UUID;

import static com.github.skapral.poetryclub.db.jooq.Tables.CONTRIBUTION;
import static org.jooq.impl.DSL.update;

/**
 * Contribution approval operation.
 *
 * @author Kapralov Sergey
 */
public class OpApproveContribution extends OpJooq {
    /**
     * Ctor.
     * @param contributionId Contribution identity
     */
    public OpApproveContribution(Scalar<UUID> contributionId) {
        super(
            new DbaPoetryClub(),
            () -> update(CONTRIBUTION).set(CONTRIBUTION.STATUS, "approved")
                .where(CONTRIBUTION.UUID.eq(contributionId.value()))
        );
    }
}
