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

package com.github.skapral.poetryclub.core.time;

import com.github.skapral.poetryclub.core.config.ConfigProperty;
import com.github.skapral.poetryclub.core.config.Cp_FAKE_SYSTIME;

/**
 * Fake time, if the system property is defined, or real time otherwise
 *
 * @author Kapralov Sergey
 */
public class SystimeAbstractedOutByProperty extends SystimeInferred {
    /**
     * Ctor.
     * @param configProperty config property.
     */
    public SystimeAbstractedOutByProperty(ConfigProperty configProperty) {
        super(
            new Inference(configProperty)
        );
    }

    /**
     * Ctor.
     */
    public SystimeAbstractedOutByProperty() {
        this(
            new Cp_FAKE_SYSTIME()
        );
    }

    /**
     * Inference
     *
     * @author Kapralov Sergey
     */
    private static class Inference implements SystemTime.Inference {
        private final ConfigProperty configProperty;

        /**
         * Ctor
         * @param configProperty config property
         */
        public Inference(ConfigProperty configProperty) {
            this.configProperty = configProperty;
        }

        @Override
        public final SystemTime systemTime() {
            if(configProperty.optionalValue().isDefined()) {
                return new SystimeFaked();
            } else {
                return new SystimeReal();
            }
        }
    }
}
