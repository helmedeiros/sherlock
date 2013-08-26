package com.br.rbs.sherlock.user;

import com.br.rbs.sherlock.user.service.UserService;
import com.br.rbs.sherlock.user.service.UserServiceImpl;

import javax.ws.rs.*;

/**
 * UserController handles the related needs for take down assumptions and notes about it,
 * so sherlock could solve the case.
 * User: helmedeiros
 * Date: 8/25/13
 * Time: 4:54 PM
 */
@Path("/users")
public class UserController {

    private UserService service = new UserServiceImpl();

    @POST
    @Path("/create")
    @Produces("text/html")
    public String create(@FormParam("customer_name") String customerName){
        return service.createUser(customerName);
    }

    @GET
    @Path("/{user}")
    @Produces("text/html")
    public String find(@PathParam("user") String user){
        return service.findUser(user);
    }

    @GET
    @Path("/list")
    @Produces("text/html")
    public String list(){
        return service.listUsers();
    }

}
