package com.br.rbs.sherlock.api.security;

import com.br.rbs.sherlock.session.domain.Session;
import com.br.rbs.sherlock.user.domain.entity.User;
import com.br.rbs.sherlock.user.domain.enums.Role;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.security.Principal;
 
/**
 * @author helmedeiros
 */
public class SecureContext implements javax.ws.rs.core.SecurityContext {
 
    private final User user;
    private final Session session;
 
    public SecureContext(Session session, User user) {
        this.session = session;
        this.user = user;
    }
 
    @Override
    public String getAuthenticationScheme() {
        return javax.ws.rs.core.SecurityContext.BASIC_AUTH;
    }
 
    @Override
    public Principal getUserPrincipal() {
        return user;
    }
 
    @Override
    public boolean isSecure() {
        return (null != session) ? session.isSecure() : false;
    }
 
    @Override
    public boolean isUserInRole(final String role) {
 
        if (null == session || !session.isActive()) {
            // Forbidden
            Response denied = Response.status(Response.Status.FORBIDDEN).entity("Permission Denied").build();
            throw new WebApplicationException(denied);
        }
 
        try {
            // this user has this role?
            return user.getRole().equals(Role.valueOf(role));
        } catch (Exception e) {
        }
         
        return false;
    }
}