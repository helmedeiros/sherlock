package com.br.rbs.sherlock.session.domain;

import com.br.rbs.sherlock.api.repository.PersistDB;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Represents a {@link com.br.rbs.sherlock.user.domain.entity.User} navigation session.
 * @author helmedeiros
 */
@Entity
@Table(name = "SESSION")
public class Session implements Serializable, PersistDB {
    private static final long serialVersionUID = -7483170872690892182L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "SESSION_ID")
    private String sessionId;   // session identifier.

    @Column(name = "USER_ID")
    private String userId;      // user identifier.

    @Column(name = "ACTIVATE")
    private boolean active;     // session is active?

    @Column(name = "SECURE")
    private boolean secure;     // session is secure?

    @Column(name = "CREATE_TIME")
    private Date createTime;    // session create time

    @Column(name = "LAST_ACCESSED_TIME")
    private Date lastAccessedTime;  // session last use time

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

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