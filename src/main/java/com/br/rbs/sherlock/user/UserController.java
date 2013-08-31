package com.br.rbs.sherlock.user;

import com.br.rbs.sherlock.user.domain.entity.User;
import com.br.rbs.sherlock.user.domain.enums.Role;
import com.br.rbs.sherlock.user.service.UserService;
import com.br.rbs.sherlock.user.service.UserServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

/**
 * UserController handles the related needs
 * User: helmedeiros
 * Date: 8/25/13
 * Time: 4:54 PM
 */
@Path("/user")
public class UserController extends AbstractController {

    private UserService service = new UserServiceImpl();

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response createUser(@Context SecurityContext sc, @FormParam("n") String customerName, @CookieParam(value = "sh_s") String sessionId){
        if (sc.isUserInRole(Role.SUBSCRIBER.name())){
            final User user = service.createUser(customerName, sessionId);
            final NewCookie sessionCookie = new NewCookie(com.br.rbs.sherlock.api.domain.enums.Cookie.SESSION.getName(), sessionId, "/", "", "", -1, false);
            return createResponse(user,sessionCookie);
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("/{userId}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response getUser(@Context SecurityContext sc, @PathParam("userId") int userId){
        if (sc.isUserInRole(Role.SUBSCRIBER.name())){
            final User user = service.findUser(userId);
            return createResponse(user);
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
