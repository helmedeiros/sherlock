package com.br.rbs.sherlock.user.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * .
 * User: helmedeiros
 * Date: 8/26/13
 * Time: 3:17 PM
 */
public class User {
    private String name;
    private List<String> anonymous = new ArrayList<String>();
    private String id;
    private String sessionId;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAnonymous(String anonymous) {
        this.anonymous.add(anonymous);
    }

    public List<String> getAnonymous() {
        return anonymous;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
