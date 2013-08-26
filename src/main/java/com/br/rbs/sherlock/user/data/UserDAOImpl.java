package com.br.rbs.sherlock.user.data;

import com.br.rbs.sherlock.user.domain.User;

import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

public class UserDAOImpl implements UserDAO {
    public static Map<String, User> users = new TreeMap<String, User>();
    public static Map<String, User> anonymousUsers = new TreeMap<String, User>();

    public UserDAOImpl() {
    }

    public User save(final String customerName, final String anonymousId) {

        User user = new User();
        user.setName(customerName);
        user.setAnonymous(anonymousId);
        user.setId(generateID());

       users.put(user.getId(), user);

        return user;
    }

    public User find(String userID) {
        return users.get(userID);
    }

    public Map<String, User> findAll() {
        return users;
    }

    @Override
    public User save(String sessionId) {
        if(!anonymousUsers.containsKey(sessionId)){
            User user = new User();
            user.setId(generateID());
            user.setSessionId(sessionId);
            user.setAnonymous(user.getId());

            anonymousUsers.put(sessionId, user);
        }

        return anonymousUsers.get(sessionId);
    }

    @Override
    public Map<String, User> findAllAnonymous() {
        return anonymousUsers;
    }

    @Override
    public boolean exist(String userId) {
        return users.containsKey(userId);
    }

    @Override
    public User findAnonymous(String sessionId) {
        return anonymousUsers.get(sessionId);
    }

    private String generateID() {
        return UUID.randomUUID().toString();
    }


}