package com.br.rbs.sherlock.user;

import com.br.rbs.sherlock.user.domain.User;
import com.br.rbs.sherlock.user.service.UserService;
import com.br.rbs.sherlock.user.service.UserServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

/**
 * UserController handles the related needs
 * User: helmedeiros
 * Date: 8/25/13
 * Time: 4:54 PM
 */
@Path("/users")
public class UsersController extends AbstractController {

    private UserService service = new UserServiceImpl();

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response list(){
        return createResponse(new ArrayList<User>(service.listUsers().values()));
    }

    @GET
    @Path("/anonymous/")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response listAnonymous(){
        return createResponse(new ArrayList<User>(service.listAnonymousUsers().values()));
    }
}
