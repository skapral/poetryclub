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

import javax.servlet.http.HttpServletRequest;

/**
 * Identify session with certain user
 *
 * @author Kapralov Sergey
 */
public class OpIdentifySession implements Operation {
    private final HttpServletRequest request;
    private final Scalar<String> login;

    /**
     * Ctor.
     * @param request request.
     * @param login User's login
     */
    public OpIdentifySession(HttpServletRequest request, Scalar<String> login) {
        this.request = request;
        this.login = login;
    }

    @Override
    public final void execute() {
        request.getSession().setAttribute("user", login.value());
    }
}
