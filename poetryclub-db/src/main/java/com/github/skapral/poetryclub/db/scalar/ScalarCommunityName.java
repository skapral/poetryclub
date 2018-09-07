package com.github.skapral.poetryclub.db.scalar;

import com.github.skapral.poetryclub.core.scalar.Scalar;
import com.github.skapral.poetryclub.db.access.DbaPoetryClub;
import org.jooq.impl.DSL;

import java.util.UUID;

import static org.jooq.generated.Tables.COMMUNITY;

/**
 * Community name by identity
 *
 * @author Kapralov Sergey
 */
public class ScalarCommunityName extends ScalarFromJooqValue<String> {
    /**
     * Ctor.
     * @param community Community identity
     */
    public ScalarCommunityName(Scalar<UUID> community) {
        super(
            new DbaPoetryClub(),
            () -> DSL.select(COMMUNITY.NAME)
                .from(COMMUNITY)
                .where(COMMUNITY.UUID.eq(community.value()))
        );
    }
}
