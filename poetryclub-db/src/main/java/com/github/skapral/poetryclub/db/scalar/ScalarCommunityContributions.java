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
import com.github.skapral.poetryclub.db.access.DbaPoetryClub;
import org.jooq.generated.tables.Community;

import java.util.UUID;

import static org.jooq.generated.tables.Account.ACCOUNT;
import static org.jooq.generated.tables.Contribution.CONTRIBUTION;
import static org.jooq.impl.DSL.select;

/**
 * A list of community contributions
 * @author Kapralov Sergey
 */
public class ScalarCommunityContributions extends ScalarFromJooqRecords {
    /**
     * Ctor.
     * @param communityId Community ID
     */
    public ScalarCommunityContributions(Scalar<UUID> communityId) {
        super(
            new DbaPoetryClub(),
            () -> select(ACCOUNT.LOGIN, CONTRIBUTION.URL, CONTRIBUTION.TIMESTAMP)
                .from(CONTRIBUTION.join(ACCOUNT).on(ACCOUNT.ID.eq(CONTRIBUTION.ACCOUNTID)))
                .where(CONTRIBUTION.COMMUNITYID.eq(
                    select(Community.COMMUNITY.ID)
                        .from(Community.COMMUNITY)
                        .where(Community.COMMUNITY.UUID.eq(communityId.value()))
                ))
                .orderBy(CONTRIBUTION.TIMESTAMP.desc())

        );
    }
}
