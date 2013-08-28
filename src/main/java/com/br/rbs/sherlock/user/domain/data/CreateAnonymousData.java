package com.br.rbs.sherlock.user.domain.data;

/**
 * Represent the data to be returned into creation of anonymous users.
 * User: helmedeiros
 * Date: 8/27/13
 * Time: 11:41 PM
 */
public class CreateAnonymousData {
    private final String sessionId;
    private final String userId;

    public CreateAnonymousData(final String userId, final String sessionId) {
        this.userId = userId;
        this.sessionId = sessionId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getUserId() {
        return userId;
    }
}
