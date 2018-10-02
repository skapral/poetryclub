package com.github.skapral.poetryclub.db.scalar;

import com.github.skapral.poetryclub.core.scalar.Scalar;
import com.github.skapral.poetryclub.core.time.SystemTime;
import com.github.skapral.poetryclub.core.time.SystimeAbstractedOutByProperty;
import com.github.skapral.poetryclub.db.access.DbaPoetryClub;

import java.time.LocalDate;
import java.time.Year;
import java.util.UUID;

import static com.github.skapral.poetryclub.db.jooq.Tables.*;
import static org.jooq.impl.DSL.*;

/**
 * List of user contributions, made in this month
 *
 * @author Kapralov Sergey
 */
public class ScalarUserContributionsThisMonth extends ScalarFromJooqRecords {
    /**
     * Ctor.
     * @param userName User login
     * @param community Community identity
     * @param time System time
     */
    public ScalarUserContributionsThisMonth(Scalar<String> userName, Scalar<UUID> community, SystemTime time) {
        super(
            new DbaPoetryClub(),
            () -> select(
                CONTRIBUTION.URL
            ).from(
                CONTRIBUTION
            ).where(
                year(CONTRIBUTION.TIMESTAMP).eq(Year.from(time.time()).getValue()),
                month(CONTRIBUTION.TIMESTAMP).eq(LocalDate.from(time.time()).getMonthValue()),
                CONTRIBUTION.COMMUNITYID.eq(
                    select(COMMUNITY.ID).from(COMMUNITY).where(COMMUNITY.UUID.eq(community.value()))
                ),
                CONTRIBUTION.ACCOUNTID.eq(
                    select(ACCOUNT.ID).from(ACCOUNT).where(ACCOUNT.LOGIN.eq(userName.value()))
                )
            )
        );
    }

    /**
     * Ctor.
     * @param userName User login
     * @param community Community identity
     */
    public ScalarUserContributionsThisMonth(Scalar<String> userName, Scalar<UUID> community) {
        this(userName, community, new SystimeAbstractedOutByProperty());
    }
}
