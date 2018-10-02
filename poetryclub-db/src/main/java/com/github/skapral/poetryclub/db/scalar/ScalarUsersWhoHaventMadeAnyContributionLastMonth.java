package com.github.skapral.poetryclub.db.scalar;

import com.github.skapral.poetryclub.core.scalar.Scalar;
import com.github.skapral.poetryclub.core.time.SystemTime;
import com.github.skapral.poetryclub.core.time.SystimeAbstractedOutByProperty;
import com.github.skapral.poetryclub.db.access.DbaPoetryClub;

import java.sql.Timestamp;
import java.time.*;
import java.util.UUID;

import static com.github.skapral.poetryclub.db.jooq.Tables.COMMUNITY;
import static com.github.skapral.poetryclub.db.jooq.Tables.CONTRIBUTION;
import static com.github.skapral.poetryclub.db.jooq.Tables.MEMBER;
import static com.github.skapral.poetryclub.db.jooq.tables.Account.ACCOUNT;
import static org.jooq.impl.DSL.*;

/**
 * Collection of users, who made no contributions in previous month
 *
 * @author Kapralov Sergey
 */
public class ScalarUsersWhoHaventMadeAnyContributionLastMonth extends ScalarFromJooqRecords {
    /**
     * Ctor.
     * @param communityId Community id
     * @param time System time
     */
    public ScalarUsersWhoHaventMadeAnyContributionLastMonth(Scalar<UUID> communityId, SystemTime time) {
        super(
            new DbaPoetryClub(),
            () -> {
                ZonedDateTime timeValue = time.time();
                YearMonth from = YearMonth.from(timeValue);
                LocalDateTime lastMonthDate = from.minusMonths(1).atEndOfMonth().atStartOfDay();
                return select(ACCOUNT.LOGIN, count(CONTRIBUTION.ID))
                    .from(ACCOUNT)
                    .innerJoin(MEMBER).on(
                        MEMBER.COMMUNITYID.eq(
                            select(COMMUNITY.ID).from(COMMUNITY).where(COMMUNITY.UUID.eq(communityId.value()))
                        ),
                        MEMBER.ACCOUNTID.eq(ACCOUNT.ID),
                        MEMBER.ROLE.ne("banned"),
                        MEMBER.TIMESTAMP.lt(new Timestamp(time.time().minusMonths(1).toInstant().toEpochMilli()))
                    )
                    .leftJoin(CONTRIBUTION).on(
                        CONTRIBUTION.ACCOUNTID.eq(ACCOUNT.ID),
                        year(CONTRIBUTION.TIMESTAMP).eq(Year.from(lastMonthDate).getValue()),
                        month(CONTRIBUTION.TIMESTAMP).eq(LocalDate.from(lastMonthDate).getMonthValue())
                    )
                    .groupBy(ACCOUNT.LOGIN)
                    .having(count(CONTRIBUTION.ID).eq(0));
            }
        );
    }

    /**
     * Ctor.
     * @param communityId Community id
     */
    public ScalarUsersWhoHaventMadeAnyContributionLastMonth(Scalar<UUID> communityId) {
        this(
            communityId,
            new SystimeAbstractedOutByProperty()
        );
    }
}
