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
import org.jooq.impl.DSL;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.YearMonth;
import java.util.UUID;

import static org.jooq.generated.Tables.*;
import static org.jooq.generated.tables.Account.ACCOUNT;
import static org.jooq.impl.DSL.*;

public class ScalarUsersWhoHaventProvidedFeedbackLastMonth extends ScalarFromJooqRecords {
    public ScalarUsersWhoHaventProvidedFeedbackLastMonth(Scalar<UUID> communityId, SystemTime time) {
        super(
            new DbaPoetryClub(),
            () -> {
                LocalDateTime timeValue = time.time();
                YearMonth from = YearMonth.from(timeValue);
                LocalDateTime lastMonthDate = from.minusMonths(1).atEndOfMonth().atStartOfDay();
                return select(ACCOUNT.LOGIN, CONTRIBUTION.URL, count(FEEDBACK.ID))
                    .from(ACCOUNT)
                    .innerJoin(MEMBER).on(
                        MEMBER.COMMUNITYID.eq(
                            select(COMMUNITY.ID).from(COMMUNITY).where(COMMUNITY.UUID.eq(communityId.value()))
                        ),
                        MEMBER.ACCOUNTID.eq(ACCOUNT.ID),
                        MEMBER.ROLE.ne("banned")
                    )
                    .leftJoin(CONTRIBUTION).on(CONTRIBUTION.ACCOUNTID.ne(ACCOUNT.ID))
                    .fullJoin(FEEDBACK).on(
                        CONTRIBUTION.ID.eq(FEEDBACK.CONTRIBUTIONID),
                        FEEDBACK.ACCOUNTID.eq(ACCOUNT.ID),
                        year(CONTRIBUTION.TIMESTAMP).eq(Year.from(lastMonthDate).getValue()),
                        month(CONTRIBUTION.TIMESTAMP).eq(LocalDate.from(lastMonthDate).getMonthValue())
                    )
                    .groupBy(ACCOUNT.LOGIN, CONTRIBUTION.URL)
                    .having(count(FEEDBACK.ID).eq(0));
            }
        );
    }

    public ScalarUsersWhoHaventProvidedFeedbackLastMonth(Scalar<UUID> communityId) {
        this(
            communityId,
            new SystimeAbstractedOutByProperty()
        );
    }
}
