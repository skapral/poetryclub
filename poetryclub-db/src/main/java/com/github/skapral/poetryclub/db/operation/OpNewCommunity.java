package com.github.skapral.poetryclub.db.operation;

import com.github.skapral.poetryclub.core.scalar.Scalar;
import com.github.skapral.poetryclub.core.time.SystemTime;
import com.github.skapral.poetryclub.core.time.SystimeAbstractedOutByProperty;
import com.github.skapral.poetryclub.db.access.DbaPoetryClub;

import java.sql.Timestamp;
import java.util.UUID;

import static com.github.skapral.poetryclub.db.jooq.Tables.*;
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
     * @param time System time
     */
    public OpNewCommunity(String communityName, Scalar<String> ownerLogin, SystemTime time) {
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
                MEMBER.ROLE,
                MEMBER.TIMESTAMP
            ).values(
                select(ACCOUNT.ID).from(ACCOUNT).where(ACCOUNT.LOGIN.eq(ownerLogin.value())).asField(),
                function("currval", Long.class, val("COMMUNITY_ID")),
                val("admin"),
                val(new Timestamp(time.time().toInstant().toEpochMilli()))
            )
        );
    }

    /**
     * Ctor.
     * @param communityName The name of comunity
     * @param ownerLogin The owner's login
     */
    public OpNewCommunity(String communityName, Scalar<String> ownerLogin) {
        this(
            communityName,
            ownerLogin,
            new SystimeAbstractedOutByProperty()
        );
    }
}
