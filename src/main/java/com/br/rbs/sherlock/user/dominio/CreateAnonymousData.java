package com.br.rbs.sherlock.user.dominio;

/**
 * .
 * User: helmedeiros
 * Date: 8/26/13
 * Time: 1:53 PM
 */
public class CreateAnonymousData extends JsonData implements JsonObject {
    private String sessionId;
    private String anonymousId;

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setAnonymousId(String anonymousId) {
        this.anonymousId = anonymousId;
    }

    public String getAnonymousId() {
        return anonymousId;
    }
}
