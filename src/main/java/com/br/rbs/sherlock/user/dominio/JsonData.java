package com.br.rbs.sherlock.user.dominio;

import org.json.JSONObject;

/**
 * .
 * User: helmedeiros
 * Date: 8/26/13
 * Time: 1:59 PM
 */
public class JsonData implements JsonObject {

    @Override
    public JSONObject toJson() {
        return new JSONObject(this);
    }
}
