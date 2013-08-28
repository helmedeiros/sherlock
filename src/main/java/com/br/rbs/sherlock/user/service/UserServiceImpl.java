package com.br.rbs.sherlock.user.service;

import com.br.rbs.sherlock.session.domain.Session;
import com.br.rbs.sherlock.session.repository.SessionRepository;
import com.br.rbs.sherlock.session.repository.SessionRepositoryImpl;
import com.br.rbs.sherlock.user.domain.User;
import com.br.rbs.sherlock.user.domain.data.CreateAnonymousData;
import com.br.rbs.sherlock.user.repository.UserRepository;
import com.br.rbs.sherlock.user.repository.UserRepositoryImpl;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.Map;

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
    public User createUser(final String customerName, final String user) {
        return userDAO.save(customerName, user);
    }

    @Override
    public User findUser(final String userId) {
        if (userDAO.exist(userId))
            return userDAO.find(userId);
        else
            throw new WebApplicationException(Response.Status.NOT_FOUND);
    }

    @Override
    public Map<String, User> listUsers() {
        return userDAO.findAll();
    }

    @Override
    public CreateAnonymousData createAnonymous(final String sessionId, final String id) {
        final User user = userDAO.saveAnonymous(id);
        final Session session = sessionDAO.saveSession(sessionId, user);

        return new CreateAnonymousData(user.getUserId(), session.getUserId());
    }

    @Override
    public Map<String, User> listAnonymousUsers() {
        return userDAO.findAllAnonymous();
    }

    @Override
    public User findAnonymousUser(String sessionId) {
        return userDAO.findAnonymousBySessionId(sessionId);
    }
}