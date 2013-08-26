package com.br.rbs.sherlock.user.service;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

/**
 * .
 * User: helmedeiros
 * Date: 8/25/13
 * Time: 10:11 PM
 */
public class UserServiceImpl implements UserService {
    public static Map<String, String> users = new TreeMap<String, String>();

    @Override
    public String createUser(final String customerName) {
        final String user = UUID.randomUUID().toString();
        users.put(user, customerName);
        return "Added user #" + user;
    }

    @Override
    public String findUser(final String user) {
        if (users.containsKey(user))
            return "<h2>Details on user #" + user + "</h2><p>Customer name: " + users.get(user);
        else
            throw new WebApplicationException(Response.Status.NOT_FOUND);
    }

    @Override
    public String listUsers() {
        String header = "<h2>All Users</h2>\n";

        header += "<ul>";
        for (Map.Entry<String, String> user : users.entrySet())
            header += "\n<li>#" + user.getKey() + " for " + user.getValue() + "</li>";

        header += "\n</ul>";

        return header;
    }
}
