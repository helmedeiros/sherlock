package com.br.rbs.sherlock.user.domain.entity;

import com.br.rbs.sherlock.api.repository.PersistDB;
import com.br.rbs.sherlock.user.domain.enums.Role;

import javax.persistence.*;
import java.security.Principal;

/**
 * Represents a system user.
 * User: helmedeiros
 */
@Entity
@Table(name = "USER")
public class User implements Principal, PersistDB {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "USER_ID")
    private String userId;          // friendly user identifier.

    @Column(name = "NAME")
    private String name;            // user name.

    @Column(name = "EMAIL_ADDRESS")
    private String emailAddress;    // email address

    @Column(name = "ROLE", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;        // roles

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getUserId() { return userId; }

    public void setUserId(String userId) { this.userId = userId; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getEmailAddress() { return emailAddress; }

    public void setEmailAddress(String emailAddress) { this.emailAddress = emailAddress; }

    public Role getRole() { return role; }

    public void setRole(Role roles) { this.role = roles; }

}
