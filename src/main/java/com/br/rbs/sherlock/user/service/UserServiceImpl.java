package com.br.rbs.sherlock.user.service;

import com.br.rbs.sherlock.user.data.UserDAO;
import com.br.rbs.sherlock.user.data.UserDAOImpl;
import com.br.rbs.sherlock.user.dominio.CreateAnonymousData;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.Map;
import java.util.Set;

/**
 * .
 * User: helmedeiros
 * Date: 8/25/13
 * Time: 10:11 PM
 */
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO = new UserDAOImpl();

    @Override
    public String createUser(final String customerName, final String user) {
        final String userId;
        userId = userDAO.save(customerName, user);
        return "Added user #" + userId;
    }

    @Override
    public String findUser(final String user) {
        if (userDAO.find(user))
            return "<h2>Details on user #" + user + "</h2><p>Customer name: " + UserDAOImpl.users.get(user);
        else
            throw new WebApplicationException(Response.Status.NOT_FOUND);
    }

    @Override
    public String listUsers() {
        return formatList(userDAO.findAll());
    }

    @Override
    public CreateAnonymousData createAnonymous(final String sessionId) {
        CreateAnonymousData data = new CreateAnonymousData();
        data.setSessionId(sessionId);
        data.setAnonymousId(userDAO.save(sessionId));
        return data;
    }

    @Override
    public String listAnonymousUsers() {
        return formatList(userDAO.findAllAnonymous());
    }

    private String formatList(Set<Map.Entry<String, String>> all) {
        String header = "<h2>All Users</h2>\n";

        header += "<ul>";
        for (Map.Entry<String, String> user : all)
            header += "\n<li>#" + user.getKey() + " for " + user.getValue() + "</li>";

        header += "\n</ul>";

        return header;
    }
}