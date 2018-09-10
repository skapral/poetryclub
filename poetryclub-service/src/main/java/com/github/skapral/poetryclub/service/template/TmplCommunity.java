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

package com.github.skapral.poetryclub.service.template;

import com.github.skapral.poetryclub.core.scalar.Scalar;
import com.github.skapral.poetryclub.db.scalar.ScalarUserIsOfRole;

import java.util.UUID;

/**
 * Community page
 *
 * @author Kapralov Sergey
 */
public class TmplCommunity extends TmplConditional {
    /**
     * Ctor.
     * @param userName User's name
     * @param communityIdentity Community identity
     */
    public TmplCommunity(Scalar<String> userName, Scalar<UUID> communityIdentity) {
        super(
            new ScalarUserIsOfRole(communityIdentity, userName, "banned"),
            new TmplBanned(userName, communityIdentity),
            new TmplConditional(
                new ScalarUserIsOfRole(communityIdentity, userName, "candidate"),
                new TmplCandidate(userName, communityIdentity),
                new TmplAgenda(userName, communityIdentity)
            )
        );
    }
}
