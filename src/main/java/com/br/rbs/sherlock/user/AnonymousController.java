package com.br.rbs.sherlock.user;

import com.br.rbs.sherlock.api.domain.enums.Cookie;
import com.br.rbs.sherlock.user.domain.User;
import com.br.rbs.sherlock.user.domain.data.CreateAnonymousData;
import com.br.rbs.sherlock.user.service.UserService;
import com.br.rbs.sherlock.user.service.UserServiceImpl;
import org.json.JSONException;

import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
    public Response create(
            @CookieParam(value = "sh_s") String sessionId,
            @CookieParam(value = "sh_u") String id) throws JSONException {

        CreateAnonymousData data = service.createAnonymous(sessionId, id);

        final NewCookie sessionCookie = new NewCookie(Cookie.SESSION.getName(), data.getSessionId(), "/", "", "", -1, false);
        final NewCookie newCookie = new NewCookie(Cookie.USER.getName(), data.getUserId(), "/", "", "", ONE_YEAR_MAX_AGE, false);

        return createResponse(data, sessionCookie, newCookie);
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response find(@CookieParam(value = "sh_u") String id){
        final User anonymousUser = service.findAnonymousUser(id);
        return createResponse(anonymousUser);
    }
}
