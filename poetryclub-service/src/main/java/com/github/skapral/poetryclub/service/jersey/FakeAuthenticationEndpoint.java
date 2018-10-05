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

package com.github.skapral.poetryclub.service.jersey;

import com.github.skapral.poetryclub.core.operation.OpSequence;
import com.github.skapral.poetryclub.core.scalar.ScalarValidName;
import com.github.skapral.poetryclub.db.operation.OpNewUser;
import com.github.skapral.poetryclub.service.operation.OpIdentifySession;
import com.pragmaticobjects.oo.atom.anno.NotAtom;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.Objects;

/**
 * Fake authentication endpoint, which just signs the session with provided user, trusting the client.
 * Convenient for testing
 *
 * @author Kapralov Sergey
 */
@NotAtom
@Path("auth")
public class FakeAuthenticationEndpoint {
    /**
     *
     * @param user User login
     * @param req Request.
     * @return Response.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response oauth(@QueryParam("user") String user, @Context HttpServletRequest req) {
        Objects.requireNonNull(user);
        new OpSequence(
            new OpIdentifySession(
                req,
                new ScalarValidName(user)
            ),
            new OpNewUser(
                new ScalarValidName(user)
            )
        ).execute();
        return Response.seeOther(URI.create("/index.html")).build();
    }
}
