package com.br.rbs.sherlock.api.cache;

import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Response;
import java.util.Date;

/**
 * User: helmedeiros
 * Date: 8/26/13
 * Time: 10:13 AM
 */
public class CacheResponseUtil {


    public static Response createResponse(Object data, String mediaType, long lastModified, int maxAge, int sMaxAge) {
        Response response;
        Response.ResponseBuilder ok = Response.ok(data, mediaType);

        if(lastModified != 0){
            response = expires(ok, lastModified, maxAge, sMaxAge).build();
        }else{
            response = ok.build();
        }

        return response;
    }

    private static Response.ResponseBuilder expires(Response.ResponseBuilder builder, long lastModified, int maxAge, int sMaxAge) {
        CacheControl cacheControl = new CacheControl();
        cacheControl.setMaxAge(maxAge);
        cacheControl.setSMaxAge(sMaxAge);

        builder.cacheControl(cacheControl);
        builder.lastModified(new Date(lastModified));

        return builder;
    }
}
