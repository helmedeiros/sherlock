package com.br.rbs.sherlock.user;

import com.br.rbs.sherlock.user.domain.enums.Role;
import com.br.rbs.sherlock.user.service.UserService;
import com.br.rbs.sherlock.user.service.UserServiceImpl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

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
    public Response list(@Context SecurityContext sc){
        if (sc.isUserInRole(Role.ANONYMOUS.name())){
            return createResponse(service.listUsers());
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

}
