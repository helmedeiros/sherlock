package com.br.rbs.sherlock.session.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Represents a {@link com.br.rbs.sherlock.user.domain.User} navigation session.
 * @author helmedeiros
 */
public class Session implements Serializable {
    private static final long serialVersionUID = -7483170872690892182L;

    private String sessionId;   // session identifier.
    private String userId;      // user identifier.
    private boolean active;     // session is active?
    private boolean secure;     // session is secure?
 
    private Date createTime;    // session create time
    private Date lastAccessedTime;  // session last use time

    public static long getSerialVersionUID() { return serialVersionUID; }

    public String getSessionId() { return sessionId; }

    public void setSessionId(final String sessionId) { this.sessionId = sessionId; }

    public String getUserId() { return userId; }

    public void setUserId(final String userId) { this.userId = userId; }

    public boolean isActive() { return active; }

    public void setActive(final boolean active) { this.active = active; }

    public boolean isSecure() { return secure; }

    public void setSecure(final boolean secure) { this.secure = secure; }

    public Date getCreateTime() { return createTime; }

    public void setCreateTime(final Date createTime) { this.createTime = createTime; }

    public Date getLastAccessedTime() { return lastAccessedTime; }

    public void setLastAccessedTime(final Date lastAccessedTime) { this.lastAccessedTime = lastAccessedTime; }
}