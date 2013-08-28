package com.br.rbs.sherlock.user;

import com.br.rbs.sherlock.user.domain.User;
import com.br.rbs.sherlock.user.service.UserService;
import com.br.rbs.sherlock.user.service.UserServiceImpl;
import org.json.JSONException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

/**
 * UserController handles the related needs
 * User: helmedeiros
 * Date: 8/25/13
 * Time: 4:54 PM
 */
@Path("/anonymous")
public class AnonymousController extends AbstractController {

    public static final int ONE_YEAR_MAX_AGE = 31536000;
    private UserService service = new UserServiceImpl();

    @GET
    @Path("/create/")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response create(@CookieParam(value = "sh_u") String id) throws JSONException {
        User data = service.createAnonymous(id);
        final NewCookie newCookie = new NewCookie("sh_u", data.getId(), "/", "", "user id", ONE_YEAR_MAX_AGE, false);

        return createResponse(data, newCookie);
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response find(@CookieParam(value = "sh_u") String id){
        final User anonymousUser = service.findAnonymousUser(id);
        return createResponse(anonymousUser);
    }
}
