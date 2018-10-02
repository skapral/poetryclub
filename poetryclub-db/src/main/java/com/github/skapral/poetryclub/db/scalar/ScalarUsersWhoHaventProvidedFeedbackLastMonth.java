/*
 * MIT License
 *
 * Copyright (c) 2018 Kapralov Sergey
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 *
 */

package com.github.skapral.poetryclub.db.scalar;

import com.github.skapral.poetryclub.core.scalar.Scalar;
import com.github.skapral.poetryclub.core.time.SystemTime;
import com.github.skapral.poetryclub.core.time.SystimeAbstractedOutByProperty;
import com.github.skapral.poetryclub.db.access.DbaPoetryClub;

import java.sql.Timestamp;
import java.time.*;
import java.util.UUID;

import static com.github.skapral.poetryclub.db.jooq.Tables.*;
import static com.github.skapral.poetryclub.db.jooq.tables.Account.ACCOUNT;
import static org.jooq.impl.DSL.*;

/**
 * Collection of users who have not provided feedback on some contribution in previous month
 *
 * @author Kapralov Sergey
 */
public class ScalarUsersWhoHaventProvidedFeedbackLastMonth extends ScalarFromJooqRecords {
    /**
     * Ctor.
     * @param communityId Community Id
     * @param time System time
     */
    public ScalarUsersWhoHaventProvidedFeedbackLastMonth(Scalar<UUID> communityId, SystemTime time) {
        super(
            new DbaPoetryClub(),
            () -> {
                ZonedDateTime timeValue = time.time();
                YearMonth from = YearMonth.from(timeValue);
                LocalDateTime lastMonthDate = from.minusMonths(1).atEndOfMonth().atStartOfDay();
                return select(ACCOUNT.LOGIN, CONTRIBUTION.URL, count(FEEDBACK.ID))
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
                        CONTRIBUTION.ACCOUNTID.ne(ACCOUNT.ID),
                        year(CONTRIBUTION.TIMESTAMP).eq(Year.from(lastMonthDate).getValue()),
                        month(CONTRIBUTION.TIMESTAMP).eq(LocalDate.from(lastMonthDate).getMonthValue())
                    )
                    .fullJoin(FEEDBACK).on(
                        CONTRIBUTION.ID.eq(FEEDBACK.CONTRIBUTIONID),
                        FEEDBACK.ACCOUNTID.eq(ACCOUNT.ID)
                    )
                    .groupBy(ACCOUNT.LOGIN, CONTRIBUTION.URL)
                    .having(count(FEEDBACK.ID).eq(0));
            }
        );
    }

    /**
     * Ctor.
     * @param communityId Community Id
     */
    public ScalarUsersWhoHaventProvidedFeedbackLastMonth(Scalar<UUID> communityId) {
        this(
            communityId,
            new SystimeAbstractedOutByProperty()
        );
    }
}
