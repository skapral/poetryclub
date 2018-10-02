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
 * Operation, adding new community member
 *
 * @author Kapralov Sergey
 */
public class OpNewCommunityMember extends OpJooq {
    /**
     * Ctor.
     * @param user User login
     * @param community Community identity
     * @param role Member's role
     * @param time System time
     */
    public OpNewCommunityMember(Scalar<String> user, Scalar<UUID> community, String role, SystemTime time) {
        super(
            new DbaPoetryClub(),
            () -> insertInto(
                MEMBER,
                MEMBER.ACCOUNTID,
                MEMBER.COMMUNITYID,
                MEMBER.ROLE,
                MEMBER.TIMESTAMP
            ).values(
                select(ACCOUNT.ID).from(ACCOUNT).where(ACCOUNT.LOGIN.eq(user.value())).asField(),
                select(COMMUNITY.ID).from(COMMUNITY).where(COMMUNITY.UUID.eq(community.value())).asField(),
                val(role),
                val(new Timestamp(time.time().toInstant().toEpochMilli()))
            )
        );
    }

    /**
     * Ctor.
     * @param user User login
     * @param community Community identity
     * @param role Member's role
     */
    public OpNewCommunityMember(Scalar<String> user, Scalar<UUID> community, String role) {
        this(user, community, role, new SystimeAbstractedOutByProperty());
    }
}
