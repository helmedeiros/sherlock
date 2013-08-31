package com.br.rbs.sherlock.api.security;

import com.br.rbs.sherlock.api.domain.enums.Cookie;
import com.br.rbs.sherlock.api.exception.PersistenceException;
import com.br.rbs.sherlock.api.util.ValidationUtil;
import com.br.rbs.sherlock.session.domain.Session;
import com.br.rbs.sherlock.session.repository.SessionRepository;
import com.br.rbs.sherlock.session.repository.SessionRepositoryImpl;
import com.br.rbs.sherlock.user.domain.entity.User;
import com.br.rbs.sherlock.user.domain.enums.Role;
import com.br.rbs.sherlock.user.repository.UserRepository;
import com.br.rbs.sherlock.user.repository.UserRepositoryImpl;
import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;
import com.sun.jersey.spi.container.ContainerResponseFilter;
import com.sun.jersey.spi.container.ResourceFilter;

import javax.ws.rs.ext.Provider;
import java.util.Collection;
import java.util.Date;

/**
 * Filter all incoming requests, look for possible session information and use that
 * to create and load a SecurityContext to request.
 * @author helmedeiros
 */

@Provider    // register as jersey's provider
public class SecurityContextFilter implements ResourceFilter, ContainerRequestFilter {
 
    private SessionRepository sessionRepository = new SessionRepositoryImpl();  // DAO to access Session
    private UserRepository userRepository = new UserRepositoryImpl();  // DAO to access User
 
     
    @Override
    public ContainerRequest filter(ContainerRequest request) {
        // Get session id from request header
        final String sessionId = request.getHeaderValue(Cookie.SESSION.getName());
 
        User user = null;
        Session session = null;
 
        if (!ValidationUtil.isEmpty(sessionId)) {
            // Load session object from repository
            session = sessionRepository.findByExactField(Session.class, "sessionId", sessionId);
             
            // Load associated user from session
            if (!ValidationUtil.isEmpty(session)) {
                user = userRepository.findByExactField(User.class, "userId", session.getUserId());;
            }
        }else{

            try {
                user = userRepository.save(null, Role.ANONYMOUS);
            } catch (PersistenceException e) {
                e.printStackTrace();
            }


            Session newSession = new Session();
            newSession.setUserId(user.getUserId());

            try {
                session = sessionRepository.save(newSession);
            } catch (PersistenceException e) {
                e.printStackTrace();
            }
        }
 
        // Set security context
        request.setSecurityContext(new SecureContext(session, user));
        return request;
    }
 
    @Override
    public ContainerRequestFilter getRequestFilter() { return this; }
 
    @Override
    public ContainerResponseFilter getResponseFilter() { return null; }
}