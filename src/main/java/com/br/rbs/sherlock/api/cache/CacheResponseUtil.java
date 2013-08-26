package com.br.rbs.sherlock.api.cache;

import org.json.JSONObject;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Auxiliary class to create responses and deal with cache.
 * User: helmedeiros
 * Date: 8/26/13
 * Time: 10:13 AM
 */
public class CacheResponseUtil {

    public static Response createResponse(JSONObject jsonObject) {
        Response.ResponseBuilder ok = Response.ok(jsonObject, MediaType.APPLICATION_JSON + ";charset=utf-8");
        return ok.build();
    }
}
