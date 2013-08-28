package com.br.rbs.sherlock.user.domain;

import com.br.rbs.sherlock.user.domain.enums.Role;

import java.security.Principal;
import java.util.Set;

/**
 * Represents a system user.
 * User: helmedeiros
 */
public class User implements Principal {

    private String userId;          //user identifier.
    private String name;            // user name.
    private String emailAddress;    // email address
    private Set<Role> roles;        // roles

    public String getUserId() { return userId; }

    public void setUserId(String userId) { this.userId = userId; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getEmailAddress() { return emailAddress; }

    public void setEmailAddress(String emailAddress) { this.emailAddress = emailAddress; }

    public Set<Role> getRoles() { return roles; }

    public void setRoles(Set<Role> roles) { this.roles = roles; }
}
