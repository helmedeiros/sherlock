package com.br.rbs.sherlock.user.data;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;

public class UserDAOImpl implements UserDAO {
    public static Map<String, String> users = new TreeMap<String, String>();
    public static Map<String, String> anonymousUsers = new TreeMap<String, String>();

    public UserDAOImpl() {
    }

    public String save(final String customerName, final String user) {
       users.put(user, customerName);
        return user;
    }

    public boolean find(String user) {
        return users.containsKey(user);
    }

    public Set<Map.Entry<String, String>> findAll() {
        return users.entrySet();
    }

    @Override
    public String save(String sessionId) {
        if(!anonymousUsers.containsKey(sessionId))
            anonymousUsers.put(sessionId, UUID.randomUUID().toString());

        return anonymousUsers.get(sessionId);
    }

}