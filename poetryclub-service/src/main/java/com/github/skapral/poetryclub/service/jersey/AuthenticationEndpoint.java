package com.github.skapral.poetryclub.service.jersey;

import com.github.scribejava.core.oauth.OAuth20Service;
import com.github.skapral.poetryclub.core.operation.OpSequence;
import com.github.skapral.poetryclub.core.scalar.Scalar;
import com.github.skapral.poetryclub.db.operation.OpNewUser;
import com.github.skapral.poetryclub.service.operation.OpIdentifySession;
import com.github.skapral.poetryclub.service.scalar.ScalarGithubUser;
import com.github.skapral.poetryclub.service.scalar.ScalarOAuthGithub;
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

/**
 * Authentication endpoint
 *
 * @author Kapralov Sergey
 */
@NotAtom
@Path("auth")
public class AuthenticationEndpoint {
    private static final Scalar<OAuth20Service> OAUTH_SERVICE = new ScalarOAuthGithub();

    /**
     * OAuth.
     *
     * @param req request.
     * @return response.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response oauth(@Context HttpServletRequest req) {
        if(req.getSession().getAttribute("user") != null) {
            return Response.seeOther(URI.create("/index.html")).build();
        }
        return Response.seeOther(
            URI.create(
                OAUTH_SERVICE.value().getAuthorizationUrl()
            )
        ).build();
    }


    /**
     * OAuth callback
     * @param code authentication code
     * @param req request.
     * @return response.
     */
    @GET
    @Path("callback")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@QueryParam("code") String code, @Context HttpServletRequest req) {
        try {
            new OpSequence(
                new OpIdentifySession(
                    req,
                    new ScalarGithubUser(OAUTH_SERVICE, code)
                ),
                new OpNewUser(
                    new ScalarGithubUser(OAUTH_SERVICE, code)
                )
            ).execute();
            return Response.seeOther(URI.create("/index.html")).build();
        } catch(Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
