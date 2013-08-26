package com.br.rbs.sherlock.user;

import com.br.rbs.sherlock.api.cache.CacheResponseUtil;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

/**
 * .
 * User: helmedeiros
 * Date: 8/26/13
 * Time: 4:29 PM
 */
public class AbstractController {
    public static final String MEDIA_TYPE = MediaType.APPLICATION_JSON + ";charset=utf-8";
    public static final int MINUTES_TO_EXPIRE = 5;
    public static final int S_MAX_AGE = 60;
    public static final int MAX_AGE = 60 * MINUTES_TO_EXPIRE;
    public static final int LAST_MODIFIED = 0;

    protected Response createResponse(Object data) {
        Map<String, Object> jsonMessageMap = new HashMap<String, Object>();

        Response response;
        jsonMessageMap.put("result", data);
        response = CacheResponseUtil.createResponse(jsonMessageMap, MEDIA_TYPE, LAST_MODIFIED, MAX_AGE, S_MAX_AGE);
        return response;
    }
}
