package com.br.rbs.sherlock.user;

import com.br.rbs.sherlock.user.domain.User;
import com.br.rbs.sherlock.user.service.UserService;
import com.br.rbs.sherlock.user.service.UserServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
    public User create(@FormParam("n") String customerName, @FormParam("a") String anonymous){
        return service.createUser(customerName, anonymous);
    }

    @GET
    @Path("/{userId}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response find(@PathParam("userId") String userId){
        final User user = service.findUser(userId);
        return createResponse(user);
    }
}
