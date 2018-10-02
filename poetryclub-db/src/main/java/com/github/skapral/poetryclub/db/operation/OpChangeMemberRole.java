package com.github.skapral.poetryclub.db.operation;

import com.github.skapral.poetryclub.core.scalar.Scalar;
import com.github.skapral.poetryclub.core.time.SystemTime;
import com.github.skapral.poetryclub.core.time.SystimeAbstractedOutByProperty;
import com.github.skapral.poetryclub.db.access.DbaPoetryClub;

import java.sql.Timestamp;
import java.util.UUID;

import static com.github.skapral.poetryclub.db.jooq.Tables.*;
import static org.jooq.impl.DSL.select;
import static org.jooq.impl.DSL.update;

/**
 * Operation, which transitions the member's role. The role is switched to the new one only if the current role is one
 * from the source list.
 *
 * @author Kapralov Sergey
 */
public class OpChangeMemberRole extends OpJooq {
    /**
     * Ctor.
     * @param userName User name
     * @param communityId Community identity
     * @param time System time
     * @param targetRole Role to set
     * @param sourceRoles List of source roles
     */
    public OpChangeMemberRole(Scalar<String> userName, Scalar<UUID> communityId, SystemTime time, String targetRole, String... sourceRoles) {
        super(
            new DbaPoetryClub(),
            () -> update(MEMBER)
                    .set(MEMBER.ROLE, targetRole)
                    .set(MEMBER.TIMESTAMP, new Timestamp(time.time().toInstant().toEpochMilli()))
                    .where(
                        MEMBER.ACCOUNTID.eq(
                            select(ACCOUNT.ID).from(ACCOUNT).where(ACCOUNT.LOGIN.eq(userName.value()))
                        ),
                        MEMBER.COMMUNITYID.eq(
                            select(COMMUNITY.ID).from(COMMUNITY).where(
                                COMMUNITY.UUID.eq(communityId.value())
                            )
                        ),
                        MEMBER.ROLE.in(sourceRoles)
                    )
        );
    }

    /**
     * Ctor.
     * @param userName User name
     * @param communityId Community identity
     * @param targetRole Role to set
     * @param sourceRoles List of source roles
     */
    public OpChangeMemberRole(Scalar<String> userName, Scalar<UUID> communityId, String targetRole, String... sourceRoles) {
        this(
            userName,
            communityId,
            new SystimeAbstractedOutByProperty(),
            targetRole,
            sourceRoles
        );
    }
}
