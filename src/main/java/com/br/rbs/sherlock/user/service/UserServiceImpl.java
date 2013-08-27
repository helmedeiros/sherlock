package com.br.rbs.sherlock.user.service;

import com.br.rbs.sherlock.user.data.UserDAO;
import com.br.rbs.sherlock.user.data.UserDAOImpl;
import com.br.rbs.sherlock.user.domain.User;
import com.br.rbs.sherlock.user.dominio.CreateAnonymousData;

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
    private final UserDAO userDAO = new UserDAOImpl();

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
    public CreateAnonymousData createAnonymous(final String sessionId) {
        CreateAnonymousData data = new CreateAnonymousData();
        data.setSessionId(sessionId);
        data.setAnonymousId(userDAO.save(sessionId).getId());
        return data;
    }

    @Override
    public Map<String, User> listAnonymousUsers() {
        return userDAO.findAllAnonymous();
    }

    @Override
    public User findAnonymousUser(String sessionId) {
        return userDAO.findAnonymous(sessionId);
    }
}