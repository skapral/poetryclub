package com.github.skapral.poetryclub.db.operation;

import com.github.skapral.poetryclub.core.scalar.Scalar;
import com.github.skapral.poetryclub.db.access.DbaPoetryClub;
import org.jooq.generated.Tables;

import java.net.URL;
import java.util.UUID;

import static org.jooq.generated.Tables.CONTRIBUTION;
import static org.jooq.impl.DSL.*;

/**
 * Operation, creating new contribution
 *
 * @author Kapralov Sergey
 */
public class OpNewContribution extends OpJooq {
    /**
     * Ctor.
     * @param community Community identity
     * @param author Author's login
     * @param url Path to the contribution
     */
    public OpNewContribution(Scalar<UUID> community, Scalar<String> author, Scalar<URL> url) {
        super(
            new DbaPoetryClub(),
            () -> insertInto(
                CONTRIBUTION,
                CONTRIBUTION.UUID,
                CONTRIBUTION.COMMUNITYID,
                CONTRIBUTION.ACCOUNTID,
                CONTRIBUTION.URL
            ).values(
                val(UUID.randomUUID()),
                select(Tables.COMMUNITY.ID).from(Tables.COMMUNITY).where(Tables.COMMUNITY.UUID.eq(community.value())).asField(),
                select(Tables.ACCOUNT.ID).from(Tables.ACCOUNT).where(Tables.ACCOUNT.LOGIN.eq(author.value())).asField(),
                val(url.value().toString())
            )
        );
    }
}
