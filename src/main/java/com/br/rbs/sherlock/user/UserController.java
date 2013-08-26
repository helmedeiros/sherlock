package com.br.rbs.sherlock.user;

import com.br.rbs.sherlock.api.cache.CacheResponseUtil;
import com.br.rbs.sherlock.user.service.UserService;
import com.br.rbs.sherlock.user.service.UserServiceImpl;
import org.json.JSONException;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * UserController handles the related needs
 * User: helmedeiros
 * Date: 8/25/13
 * Time: 4:54 PM
 */
@Path("/users")
public class UserController {

    private UserService service = new UserServiceImpl();

    @GET
    @Path("/anonymous")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response createAnonymous(@QueryParam("s") String sessionId) throws JSONException {
        Response response;

        try {
            String anonymous = service.createAnonymous(sessionId);
            response = CacheResponseUtil.createResponse(new JSONObject().put("a", anonymous));
        } catch (JSONException e) {
            response = CacheResponseUtil.createResponse(new JSONObject().put("error", e.getMessage()));
        }

        return response;
    }

    @POST
    @Path("/create")
    @Produces("text/html")
    public String create(@FormParam("n") String customerName, @FormParam("a") String anonymous){
        return service.createUser(customerName, anonymous);
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

    @GET
    @Path("/list/anonymous")
    @Produces("text/html")
    public String listAnonymous(){
        return service.listAnonymousUsers();
    }
}
