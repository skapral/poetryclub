package com.github.skapral.poetryclub.db.scalar;

import com.github.skapral.poetryclub.core.scalar.Scalar;
import com.github.skapral.poetryclub.core.time.SystemTime;
import com.github.skapral.poetryclub.db.access.DbaPoetryClub;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.YearMonth;
import java.util.UUID;

import static java.time.YearMonth.from;
import static org.jooq.generated.Tables.COMMUNITY;
import static org.jooq.generated.Tables.CONTRIBUTION;
import static org.jooq.generated.tables.Account.ACCOUNT;
import static org.jooq.impl.DSL.*;

public class ScalarUsersWithoutContributionsLastMonth extends ScalarFromJooqRecords {
    public ScalarUsersWithoutContributionsLastMonth(Scalar<UUID> communityId, SystemTime time) {
        super(
            new DbaPoetryClub(),
            () -> {
                LocalDateTime timeValue = time.time();
                YearMonth from = from(timeValue);
                LocalDateTime lastMonthDate = from.minusMonths(1).atEndOfMonth().atStartOfDay();
                return select(ACCOUNT.LOGIN)
                        .from(
                            select(ACCOUNT.LOGIN, coalesce(count(CONTRIBUTION.ID), 0).as("CONTRIBUTIONS_COUNT"))
                                .from(CONTRIBUTION.rightJoin(ACCOUNT).on(ACCOUNT.ID.eq(CONTRIBUTION.ACCOUNTID)))
                                .where(
                                    year(CONTRIBUTION.TIMESTAMP).eq(Year.from(lastMonthDate).getValue()),
                                    month(CONTRIBUTION.TIMESTAMP).eq(LocalDate.from(lastMonthDate).getMonthValue()),
                                    CONTRIBUTION.COMMUNITYID.eq(
                                        select(COMMUNITY.ID).from(COMMUNITY).where(COMMUNITY.UUID.eq(communityId.value()))
                                    )
                                )
                        )
                        .where(
                            field("CONTRIBUTIONS_COUNT").eq(0)
                        );
            }
        );
    }
}
