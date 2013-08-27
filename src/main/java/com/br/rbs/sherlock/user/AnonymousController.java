package com.br.rbs.sherlock.user;

import com.br.rbs.sherlock.user.domain.User;
import com.br.rbs.sherlock.user.dominio.CreateAnonymousData;
import com.br.rbs.sherlock.user.service.UserService;
import com.br.rbs.sherlock.user.service.UserServiceImpl;
import org.json.JSONException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * UserController handles the related needs
 * User: helmedeiros
 * Date: 8/25/13
 * Time: 4:54 PM
 */
@Path("/anonymous")
public class AnonymousController extends AbstractController {

    private UserService service = new UserServiceImpl();

    @GET
    @Path("/create/{s}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response create(@PathParam("s") String sessionId) throws JSONException {
        CreateAnonymousData data = service.createAnonymous(sessionId);
        return createResponse(data);
    }

    @GET
    @Path("/{s}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response find(@PathParam("s") String sessionId){
        final User anonymousUser = service.findAnonymousUser(sessionId);
        return createResponse(anonymousUser);
    }
}
