package com.br.rbs.sherlock.user.service;

import com.br.rbs.sherlock.api.exception.PersistenceException;
import com.br.rbs.sherlock.api.util.ValidationUtil;
import com.br.rbs.sherlock.session.domain.Session;
import com.br.rbs.sherlock.session.repository.SessionRepository;
import com.br.rbs.sherlock.session.repository.SessionRepositoryImpl;
import com.br.rbs.sherlock.user.domain.entity.User;
import com.br.rbs.sherlock.user.domain.enums.Role;
import com.br.rbs.sherlock.user.repository.UserRepository;
import com.br.rbs.sherlock.user.repository.UserRepositoryImpl;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.Collection;

/**
 * .
 * User: helmedeiros
 * Date: 8/25/13
 * Time: 10:11 PM
 */
public class UserServiceImpl implements UserService {
    private final UserRepository userDAO = new UserRepositoryImpl();
    private final SessionRepository sessionDAO = new SessionRepositoryImpl();

    @Override
    public User createUser(final String customerName, final String sessionId) {
        User newUser = null;

        try {
            newUser = getUserFromSession(sessionId);

            newUser = userDAO.update(customerName, newUser.getId(), Role.SUBSCRIBER);
        } catch (PersistenceException e) {
            e.printStackTrace();
        }

        return newUser;
    }

    private User getUserFromSession(String sessionId) {
        User newUser = null;
        Session session = sessionDAO.findByExactField(Session.class, "sessionId", sessionId);

        // Load associated user from session
        if (!ValidationUtil.isEmpty(session)) {
            newUser = userDAO.findByExactField(User.class, "userId", session.getUserId());;
        }
        return newUser;
    }

   @Override
    public User findUser(final int userId) {
        if(ValidationUtil.isEmpty(userId)){
            throw new WebApplicationException(Response.Status.PRECONDITION_FAILED);
        }

        User user = userDAO.find(userId);

        if(ValidationUtil.isEmpty(user))
            throw new WebApplicationException(Response.Status.NOT_FOUND);

        return user;
    }

    @Override
    public Collection<User> listUsers() {
        return userDAO.findAll();
    }
}