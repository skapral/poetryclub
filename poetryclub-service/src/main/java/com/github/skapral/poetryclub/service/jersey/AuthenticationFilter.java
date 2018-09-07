package com.github.skapral.poetryclub.service.jersey;

import com.pragmaticobjects.oo.atom.anno.NotAtom;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.net.URI;


/**
 * Authentication filter
 *
 * @author Kapralov Sergey
 */
@NotAtom
@Provider
public class AuthenticationFilter implements ContainerRequestFilter {
    @Context
    private HttpServletRequest req;

    /**
     * @param requestContext request context
     */
    @Override
    public void filter(ContainerRequestContext requestContext) {
        if(req.getSession().getAttribute("user") == null && !requestContext.getUriInfo().getPath().startsWith("auth")) {
            requestContext.abortWith(
                Response.seeOther(
                    URI.create("auth")
                ).build()
            );
        }
    }
}
