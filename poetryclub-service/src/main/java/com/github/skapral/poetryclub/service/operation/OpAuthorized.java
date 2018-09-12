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

package com.github.skapral.poetryclub.service.operation;

import com.github.skapral.poetryclub.core.operation.Operation;
import com.github.skapral.poetryclub.core.scalar.Scalar;
import com.github.skapral.poetryclub.db.scalar.ScalarUserRole;
import com.github.skapral.poetryclub.service.scalar.ScalarCurrentUser;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;
import java.util.function.Predicate;

/**
 * Operation, which is executed if the currently logged in user has certain role in community
 *
 * @author Kapralov Sergey
 */
public class OpAuthorized implements Operation {
    private final Scalar<String> role;
    private final Predicate<String> expectedRole;
    private final Operation delegate;

    /**
     * Ctor.
     * @param role Actual role
     * @param expectedRole Expected role
     * @param delegate Operation to delegate to
     */
    private OpAuthorized(Scalar<String> role, Predicate<String> expectedRole, Operation delegate) {
        this.role = role;
        this.expectedRole = expectedRole;
        this.delegate = delegate;
    }

    /**
     * Ctor.
     * @param req Request
     * @param communityId Community identity
     * @param expectedRole Expected role in community
     * @param delegate Operation to delegate to
     */
    public OpAuthorized(HttpServletRequest req, Scalar<UUID> communityId, Predicate<String> expectedRole, Operation delegate) {
        this(
            new ScalarUserRole(
                communityId,
                new ScalarCurrentUser(req)
            ),
            expectedRole,
            delegate
        );
    }

    @Override
    public final void execute() {
        if(expectedRole.test(role.value())) {
            delegate.execute();
        } else {
            throw new RuntimeException("Attempt to execute unauthorized operation");
        }
    }
}
