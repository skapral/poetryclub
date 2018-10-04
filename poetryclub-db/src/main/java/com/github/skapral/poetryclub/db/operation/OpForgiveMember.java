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

package com.github.skapral.poetryclub.db.operation;

import com.github.skapral.poetryclub.core.scalar.Scalar;
import com.github.skapral.poetryclub.core.time.SystemTime;
import com.github.skapral.poetryclub.core.time.SystimeAbstractedOutByProperty;
import com.github.skapral.poetryclub.db.access.DbaPoetryClub;

import java.util.UUID;

import static com.github.skapral.poetryclub.db.jooq.Tables.*;
import static org.jooq.impl.DSL.*;

/**
 * Forgive member by resetting the timestamp of its membership
 * 
 * @author skapral
 */
public class OpForgiveMember extends OpJooq {
    /**
     * Ctor.
     * 
     * @param accountLogin Account login
     * @param community Community
     * @param time System time
     */
    public OpForgiveMember(Scalar<String> accountLogin, Scalar<UUID> community, SystemTime time) {
        super(
            new DbaPoetryClub(),
            () -> update(MEMBER)
                .set(MEMBER.TIMESTAMP, currentTimestamp())
                .where(
                    MEMBER.ACCOUNTID.eq(
                        select(ACCOUNT.ID).from(ACCOUNT).where(ACCOUNT.LOGIN.eq(accountLogin.value()))
                    ),
                    MEMBER.COMMUNITYID.eq(
                        select(COMMUNITY.ID).from(COMMUNITY).where(COMMUNITY.UUID.eq(community.value()))
                    )
                )
        );
    }

    /**
     * Ctor.
     * 
     * @param accountLogin Account login
     * @param community Community
     */
    public OpForgiveMember(Scalar<String> accountLogin, Scalar<UUID> community) {
        this(accountLogin, community, new SystimeAbstractedOutByProperty());
    }
}
