package com.br.rbs.sherlock.api.cache;

import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.util.Date;

/**
 * Auxiliary class to work with {@link Response}.
 * User: helmedeiros
 * Date: 8/26/13
 * Time: 10:13 AM
 */
public class ResponseUtil {


    /**
     * Create and configure a {@link Response}, with cache information, {@link javax.ws.rs.core.MediaType} also {@link NewCookie}.
     * @param data - The main information to be set into {@link Response}.
     * @param mediaType -  The {@link javax.ws.rs.core.MediaType} to be set to {@link Response}.
     * @param lastModified - The {@link long} representation of the last modified time.
     * @param maxAge - The value of the max-age cache control directive, a value of -1 will disable the directive.
     * @param sMaxAge - The value of the s-maxage cache control directive, -1 if the directive is disabled.
     * @param cookies - New cookies that will accompany the response. A null value will remove all cookies, including those added via the header(java.lang.String, java.lang.Object) method.
     * @return A complete and configured {@link Response}.
     */
    public static Response createResponse(Object data, String mediaType, long lastModified, int maxAge, int sMaxAge, NewCookie... cookies) {
        Response response;
        Response.ResponseBuilder ok = Response.ok(data, mediaType);

        defineCookies(ok, cookies);

        if(lastModified != 0){
            response = expires(ok, lastModified, maxAge, sMaxAge).build();
        }else{
            response = ok.build();
        }

        return response;
    }

    /**
     * Define the given {@link NewCookie}s into {@link Response.ResponseBuilder}.
     * @param builder - The {@link Response.ResponseBuilder} that will receive any existent {@link NewCookie}.
     * @param cookies - The given {@link NewCookie} varargs to be set into the  {@link Response.ResponseBuilder}.
     */
    private static void defineCookies(Response.ResponseBuilder builder, NewCookie... cookies) {
        for (NewCookie cookie : cookies) {
            builder.cookie(cookie);
        }
    }

    /**
     * Define all cache information into the given {@link Response.ResponseBuilder}.
     * @param builder - The {@link javax.ws.rs.core.Response.ResponseBuilder} that will be configured with all cache information.
     * @param lastModified - The {@link long} representation of the last modified time.
     * @param maxAge - The value of the max-age cache control directive, a value of -1 will disable the directive.
     * @param sMaxAge - The value of the s-maxage cache control directive, -1 if the directive is disabled.
     * @return The {@link javax.ws.rs.core.Response.ResponseBuilder} with cache configuration.
     */
    private static Response.ResponseBuilder expires(Response.ResponseBuilder builder, long lastModified, int maxAge, int sMaxAge) {
        CacheControl cacheControl = new CacheControl();
        cacheControl.setMaxAge(maxAge);
        cacheControl.setSMaxAge(sMaxAge);

        builder.cacheControl(cacheControl);
        builder.lastModified(new Date(lastModified));

        return builder;
    }
}
