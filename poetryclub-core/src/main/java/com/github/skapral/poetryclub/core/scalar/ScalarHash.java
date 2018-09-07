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

package com.github.skapral.poetryclub.core.scalar;

import io.vavr.Tuple2;
import io.vavr.collection.HashMap;
import io.vavr.collection.List;

import java.util.Map;

/**
 * Hash-valued scalar.
 *
 * @param <K> Hash key type.
 * @param <V> Hash value type.
 */
public class ScalarHash<K, V> implements Scalar<Map<K, V>> {
    private final List<Tuple2<K, V>> tuples;

    /**
     * @param tuples List of tuples.
     */
    public ScalarHash(List<Tuple2<K, V>> tuples) {
        this.tuples = tuples;
    }

    /**
     * @param tuples List of tuples.
     */
    public ScalarHash(Tuple2<K, V>... tuples) {
        this(List.of(tuples));
    }

    @Override
    public final Map<K, V> value() {
        java.util.HashMap<K, V> map = HashMap.ofEntries(tuples).toJavaMap();
        return map;
    }
}
