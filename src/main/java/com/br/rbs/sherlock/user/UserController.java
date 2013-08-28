package com.br.rbs.sherlock.user;

import com.br.rbs.sherlock.user.domain.User;
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

    private static final int ONE_YEAR_MAX_AGE = 31536000;
    private UserService service = new UserServiceImpl();

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @javax.annotation.security.RolesAllowed({ "Administrator" })
    public Response create(@Context SecurityContext sc, @FormParam("n") String customerName,
                           @CookieParam(value = "sh_u") String id){
        final User user = service.createUser(customerName, id);
        final NewCookie newCookie = new NewCookie("sh_u", user.getUserId(), "/", "", "user id", ONE_YEAR_MAX_AGE, false);

        return createResponse(user,newCookie);
    }

    @GET
    @Path("/{userId}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @javax.annotation.security.RolesAllowed({ "Administrator" })
    public Response find(@Context SecurityContext sc, @PathParam("userId") String userId){
        final User user = service.findUser(userId);
        return createResponse(user);
    }
}
