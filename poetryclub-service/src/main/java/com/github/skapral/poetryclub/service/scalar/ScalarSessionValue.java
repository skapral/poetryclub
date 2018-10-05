/*
 * The MIT License
 *
 * Copyright 2018 skapral.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.github.skapral.poetryclub.service.scalar;

import com.github.skapral.poetryclub.core.scalar.Scalar;
import java.util.Objects;
import java.util.function.Supplier;
import javax.servlet.http.HttpServletRequest;

/**
 * Scalar with value, memoized at session's attributes
 * 
 * @param <T> Scalar type.
 * @author skapral
 */
public class ScalarSessionValue<T> implements Scalar<T> {
    private final HttpServletRequest req;
    private final String key;
    private final Supplier<T> objectSupplier;

    /**
     * @param req Request.
     * @param key Memoization key.
     * @param objectSupplier Lazy evaluation function.
     */
    public ScalarSessionValue(HttpServletRequest req, String key, Supplier<T> objectSupplier) {
        this.req = req;
        this.key = key;
        this.objectSupplier = objectSupplier;
    }

    @Override
    public final T value() {
        Object memoizedValue = req.getSession().getAttribute(this.getClass().getName() + "#" + key);
        if(Objects.isNull(memoizedValue)) {
            memoizedValue = objectSupplier.get();
            req.getSession().setAttribute(this.getClass().getName() + "#" + key, memoizedValue);
        }
        return (T) memoizedValue;
    }
}
