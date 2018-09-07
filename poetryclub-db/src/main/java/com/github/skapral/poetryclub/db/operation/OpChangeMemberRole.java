package com.github.skapral.poetryclub.db.operation;

import com.github.skapral.poetryclub.core.scalar.Scalar;
import com.github.skapral.poetryclub.db.access.DbaPoetryClub;

import java.util.UUID;

import static org.jooq.generated.Tables.*;
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
     *
     * @param userName User name
     * @param communityId Community identity
     * @param targetRole Role to set
     * @param sourceRoles List of source roles
     */
    public OpChangeMemberRole(Scalar<String> userName, Scalar<UUID> communityId, String targetRole, String... sourceRoles) {
        super(
            new DbaPoetryClub(),
            () -> update(MEMBER)
                    .set(MEMBER.ROLE, targetRole)
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
}
