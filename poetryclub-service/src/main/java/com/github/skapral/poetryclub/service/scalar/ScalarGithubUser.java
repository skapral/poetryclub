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

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.github.skapral.poetryclub.core.scalar.Scalar;
import org.json.JSONObject;

import java.util.Objects;
import javax.servlet.http.HttpServletRequest;

/**
 * User, obtained from GitHub authentication code
 * @author Kapralov Sergey
 */
public class ScalarGithubUser extends ScalarSessionValue<String> {
    /**
     * Ctor.
     * @param req request
     * @param oauth oauth service
     * @param authorizationCode authorization code from GitHub callback
     */
    public ScalarGithubUser(HttpServletRequest req, Scalar<OAuth20Service> oauth, String authorizationCode) {
        super(
            req,
            authorizationCode,
            () -> {
                try {
                    OAuth20Service service = oauth.value();
                    final OAuth2AccessToken accessToken = service.getAccessToken(authorizationCode);
                    final OAuthRequest request = new OAuthRequest(Verb.GET, "https://api.github.com/user");
                    service.signRequest(accessToken, request);
                    final com.github.scribejava.core.model.Response resp = service.execute(request);
                    final String login = new JSONObject(resp.getBody()).getString("login");
                    Objects.requireNonNull(login, "Cannot obtain login - " + resp.getCode() + ": " + resp.getBody());
                    return login;
                } catch(Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        );
    }
}
