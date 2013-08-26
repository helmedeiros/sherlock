package com.br.rbs.sherlock.user;

import com.br.rbs.sherlock.api.cache.CacheResponseUtil;
import com.br.rbs.sherlock.user.dominio.CreateAnonymousData;
import com.br.rbs.sherlock.user.service.UserService;
import com.br.rbs.sherlock.user.service.UserServiceImpl;
import org.json.JSONException;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

/**
 * UserController handles the related needs
 * User: helmedeiros
 * Date: 8/25/13
 * Time: 4:54 PM
 */
@Path("/users")
public class UserController {

    public static final String MEDIA_TYPE = MediaType.APPLICATION_JSON + ";charset=utf-8";
    public static final int MINUTES_TO_EXPIRE = 5;
    public static final int S_MAX_AGE = 60;
    public static final int MAX_AGE = 60 * MINUTES_TO_EXPIRE;
    public static final int LAST_MODIFIED = 0;
    private UserService service = new UserServiceImpl();

    @GET
    @Path("/anonymous")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response createAnonymous(@QueryParam("s") String sessionId) throws JSONException {
        Response response;
        Map<String, JSONObject> jsonMessageMap = new HashMap<String, JSONObject>();

        CreateAnonymousData data = service.createAnonymous(sessionId);
        jsonMessageMap.put("result", data.toJson());
        response = CacheResponseUtil.createResponse(new JSONObject(jsonMessageMap).toString(), MEDIA_TYPE, LAST_MODIFIED, MAX_AGE, S_MAX_AGE);

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
