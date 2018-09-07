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

package com.github.skapral.poetryclub.service.scalar;

import com.github.scribejava.apis.GitHubApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.github.skapral.poetryclub.core.config.ConfigProperty;
import com.github.skapral.poetryclub.core.scalar.ScalarMemoizedValue;
import com.github.skapral.poetryclub.service.config.Cp_GITHUB_API_KEY;
import com.github.skapral.poetryclub.service.config.Cp_GITHUB_API_SECRET;

/**
 * Scribe OAuth GitHub service
 *
 * @author Kapralov Sergey
 */
public class ScalarOAuthGithub extends ScalarMemoizedValue<OAuth20Service> {
    /**
     * Ctor.
     * @param apiKey GitHub API key
     * @param apiSecret GitHub API secret
     */
    public ScalarOAuthGithub(ConfigProperty apiKey, ConfigProperty apiSecret) {
        super(
            "",
            () -> new ServiceBuilder(apiKey.optionalValue().get())
                    .apiSecret(apiSecret.optionalValue().get())
                    .callback("")
                    .build(GitHubApi.instance())
        );
    }

    /**
     * Ctor.
     */
    public ScalarOAuthGithub() {
        this(
            new Cp_GITHUB_API_KEY(),
            new Cp_GITHUB_API_SECRET()
        );
    }
}
