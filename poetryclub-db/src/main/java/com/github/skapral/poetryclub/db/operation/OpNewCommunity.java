package com.github.skapral.poetryclub.db.operation;

import com.github.skapral.poetryclub.core.scalar.Scalar;
import com.github.skapral.poetryclub.db.access.DbaPoetryClub;

import java.util.UUID;

import static org.jooq.generated.Tables.*;
import static org.jooq.impl.DSL.*;

/**
 * Operation, creating new community
 *
 * @author Kapralov Sergey
 */
public class OpNewCommunity extends OpJooq {
    /**
     * Ctor.
     * @param communityName The name of comunity
     * @param ownerLogin The owner's login
     */
    public OpNewCommunity(String communityName, Scalar<String> ownerLogin) {
        super(
            new DbaPoetryClub(),
            () -> insertInto(
                COMMUNITY,
                COMMUNITY.UUID,
                COMMUNITY.NAME
            ).values(
                UUID.randomUUID(),
                communityName
            ),
            () -> insertInto(
                MEMBER,
                MEMBER.ACCOUNTID,
                MEMBER.COMMUNITYID,
                MEMBER.ROLE
            ).values(
                select(ACCOUNT.ID).from(ACCOUNT).where(ACCOUNT.LOGIN.eq(ownerLogin.value())).asField(),
                function("currval", Long.class, val("COMMUNITY_ID")),
                val("admin")
            )
        );
    }
}
