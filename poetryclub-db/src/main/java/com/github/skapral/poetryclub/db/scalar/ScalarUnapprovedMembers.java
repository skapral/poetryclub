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

import java.util.UUID;

import static com.github.skapral.poetryclub.db.jooq.Tables.*;
import static org.jooq.impl.DSL.select;

/**
 * A list of unapproved members in certain community
 *
 * @author Kapralov Sergey
 */
public class ScalarUnapprovedMembers extends ScalarFromJooqRecords {
    /**
     * Ctor
     * @param communityId Community identity
     */
    public ScalarUnapprovedMembers(Scalar<UUID> communityId) {
        super(
            new DbaPoetryClub(),
            () -> select(ACCOUNT.LOGIN, COMMUNITY.UUID)
                .from(
                    MEMBER
                        .join(COMMUNITY).on(COMMUNITY.ID.eq(MEMBER.COMMUNITYID))
                        .join(ACCOUNT).on(ACCOUNT.ID.eq(MEMBER.ACCOUNTID))
                )
                .where(
                    MEMBER.COMMUNITYID.eq(
                        select(COMMUNITY.ID).from(COMMUNITY).where(COMMUNITY.UUID.eq(communityId.value()))
                    ),
                    MEMBER.ROLE.eq("candidate")
                )
        );
    }
}
