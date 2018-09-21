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

import com.github.skapral.poetryclub.core.scalar.ScalarStatic;
import com.github.skapral.poetryclub.core.scalar.ScalarURL;
import com.github.skapral.poetryclub.core.scalar.ScalarUUID;
import com.github.skapral.poetryclub.db.operation.*;
import com.github.skapral.poetryclub.service.operation.OpAuthorizedForAdmin;
import com.github.skapral.poetryclub.service.operation.OpAuthorizedForMembers;
import com.github.skapral.poetryclub.service.scalar.ScalarCurrentUser;
import com.github.skapral.poetryclub.service.template.TmplCommunity;
import com.github.skapral.poetryclub.service.template.TmplIndex;
import com.github.skapral.poetryclub.service.template.TmplSummary;
import com.pragmaticobjects.oo.atom.anno.NotAtom;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URLEncoder;

/**
 * Poetryclub main endpoint
 *
 * @author Kapralov Sergey
 */
@NotAtom
@Path("/")
public class PoetryEndpoint {
    /**
     * index page
     * @param req Request
     * @return Response
     */
    @GET
    @Path("index.html")
    public String index(@Context HttpServletRequest req) {
        return new TmplIndex(
            new ScalarCurrentUser(req)
        ).content();
    }

    /**
     * Summary page
     * @param req Request
     * @param communityId Community ID
     * @return Response
     */
    @GET
    @Path("{communityId}/summary.html")
    public String summary(@Context HttpServletRequest req, @PathParam("communityId") String communityId) {
        return new TmplSummary(
            new ScalarUUID(communityId)
        ).content();
    }

    /**
     * Community agenda page
     * @param req Request.
     * @param communityId Community identity
     * @return Response
     */
    @GET
    @Path("{communityId}/index.html")
    public String communityView(@Context HttpServletRequest req, @PathParam("communityId") String communityId) {
        return new TmplCommunity(
            new ScalarCurrentUser(req),
            new ScalarUUID(communityId)
        ).content();
    }

    /**
     *
     * @param name Community name
     * @param req Request
     * @return Response
     */
    @POST
    @Path("newcommunity")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response newCommunity(@FormParam("name") String name, @Context HttpServletRequest req) {
        new OpNewCommunity(
            name,
            new ScalarCurrentUser(req)
        ).execute();
        return Response.seeOther(URI.create("index.html")).build();
    }

    /**
     *
     * @param uuid Community identity
     * @param req Request
     * @return Response
     */
    @POST
    @Path("joincommunity")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response joinCommunity(@FormParam("uuid") String uuid, @Context HttpServletRequest req) {
        new OpNewCommunityMember(
            new ScalarCurrentUser(req),
            new ScalarUUID(uuid),
            "candidate"
        ).execute();
        return Response.seeOther(URI.create("index.html")).build();
    }

    /**
     *
     * @param communityId community identity
     * @param url new contributions url
     * @param req Request
     * @return Response
     * @throws Exception if something goes wrong
     */
    @POST
    @Path("{communityId}/newcontribution")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response newContribution(@PathParam("communityId") String communityId,
                                    @FormParam("url") String url,
                                    @Context HttpServletRequest req) throws Exception {
        new OpAuthorizedForMembers(
            req,
            new ScalarUUID(communityId),
            new OpNewContribution(
                new ScalarUUID(communityId),
                new ScalarCurrentUser(req),
                new ScalarURL(url)
            )
        ).execute();
        return Response.seeOther(URI.create(URLEncoder.encode(communityId, "UTF-8") + "/index.html")).build();
    }

    /**
     *
     * @param communityId Community identity
     * @param contributionId Contribution identity
     * @param url Feedback's URL
     * @param req Request
     * @return Response
     * @throws Exception if something goes wrong
     */
    @POST
    @Path("{communityId}/{contrbutionId}/newfeedback")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response newFeedback(@PathParam("communityId") String communityId,
                                @PathParam("contrbutionId") String contributionId,
                                @FormParam("url") String url,
                                @Context HttpServletRequest req) throws Exception {
        new OpAuthorizedForMembers(
            req,
            new ScalarUUID(communityId),
            new OpNewFeedback(
                new ScalarCurrentUser(req),
                new ScalarUUID(contributionId),
                new ScalarURL(url)
            )
        ).execute();
        return Response.seeOther(URI.create(URLEncoder.encode(communityId, "UTF-8") + "/index.html")).build();
    }

    /**
     *
     * @param communityId Community identity
     * @param contributionId Contribution identity
     * @param req Request
     * @return Response
     * @throws Exception if something goes wrong
     */
    @POST
    @Path("{communityId}/{contrbutionId}/approveContribution")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response approveContribution(@PathParam("communityId") String communityId,
                                        @PathParam("contrbutionId") String contributionId,
                                        @Context HttpServletRequest req) throws Exception {
        new OpAuthorizedForAdmin(
            req,
            new ScalarUUID(communityId),
            new OpApproveContribution(
                new ScalarUUID(contributionId)
            )
        ).execute();
        return Response.seeOther(URI.create(URLEncoder.encode(communityId, "UTF-8") + "/index.html")).build();
    }


    /**
     *
     * @param communityId Community identity
     * @param contributionId Contribution identity
     * @param feedbackId Feedback identity
     * @param req Request
     * @return Response
     * @throws Exception if something goes wrong
     */
    @POST
    @Path("{communityId}/{contrbutionId}/{feedbackId}/approveFeedback")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response approveFeedback(@PathParam("communityId") String communityId,
                                    @PathParam("contrbutionId") String contributionId,
                                    @PathParam("feedbackId") String feedbackId,
                                    @Context HttpServletRequest req) throws Exception {
        new OpAuthorizedForAdmin(
            req,
            new ScalarUUID(communityId),
            new OpApproveFeedback(
                new ScalarUUID(feedbackId)
            )
        ).execute();
        return Response.seeOther(URI.create(URLEncoder.encode(communityId, "UTF-8") + "/index.html")).build();
    }

    /**
     *
     * @param communityId Community identity
     * @param userLogin User Login
     * @param req Request
     * @return Response
     * @throws Exception if something goes wrong
     */
    @POST
    @Path("{communityId}/{user}/approveMembership")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response approveMembership(@PathParam("communityId") String communityId,
                                      @PathParam("user") String userLogin,
                                      @Context HttpServletRequest req) throws Exception {
        new OpAuthorizedForAdmin(
            req,
            new ScalarUUID(communityId),
            new OpApproveMember(
                new ScalarStatic<>(userLogin),
                new ScalarUUID(communityId)
            )
        ).execute();
        return Response.seeOther(URI.create(URLEncoder.encode(communityId, "UTF-8") + "/index.html")).build();
    }

    /**
     *
     * @param communityId Community identity
     * @param userLogin User Login
     * @param req Request
     * @return Response
     * @throws Exception if something goes wrong
     */
    @POST
    @Path("{communityId}/{user}/banMember")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response banMember(@PathParam("communityId") String communityId,
                              @PathParam("user") String userLogin,
                              @Context HttpServletRequest req) throws Exception {
        new OpAuthorizedForAdmin(
            req,
            new ScalarUUID(communityId),
            new OpBanMember(
                new ScalarStatic<>(userLogin),
                new ScalarUUID(communityId)
            )
        ).execute();
        return Response.seeOther(URI.create(URLEncoder.encode(communityId, "UTF-8") + "/index.html")).build();
    }
}
