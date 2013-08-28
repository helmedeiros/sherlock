package com.br.rbs.sherlock.user.repository;

import com.br.rbs.sherlock.api.util.ValidationUtil;
import com.br.rbs.sherlock.user.domain.User;
import com.br.rbs.sherlock.user.domain.enums.Role;

import java.util.*;

public class UserRepositoryImpl implements UserRepository {
    public static Map<String, User> users = new TreeMap<String, User>();
    public static Map<String, User> anonymousUsers = new TreeMap<String, User>();

    public UserRepositoryImpl() {
    }

    public User save(final String customerName, final String id) {
        //Verify if the user id is from anonymous or not.
        User user = findAnonymousById(id);

        if(ValidationUtil.isEmpty(id) || !ValidationUtil.isEmpty(user)){
            user.setName(customerName);
            user.setUserId(generateID());

            users.put(user.getUserId(), user);
        }else{
            user = find(id);
        }

        return user;
    }

    public User find(String userID) {
        return users.get(userID);
    }

    public Map<String, User> findAll() {
        return users;
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
    public User findAnonymousBySessionId(String sessionId) {
        return anonymousUsers.get(sessionId);
    }

    @Override
    public User findAnonymousById(final String id) {

        User user = null;
        for (Map.Entry<String, User> cod : anonymousUsers.entrySet()) {
            if (cod.getValue().getUserId().equals(id)){
                user = cod.getValue();
            }
        }
        return user;
    }

    @Override
    public User saveAnonymous(final String id) {
        User user = findAnonymousById(id);

        if (ValidationUtil.isEmpty(user)){

            final String newId = generateID();
            user = new User();
            user.setUserId(newId);

            Set<Role> roles = new TreeSet<Role>();
            roles.add(Role.SUBSCRIBER);

            user.setRoles(roles);
            anonymousUsers.put(newId, user);
        }

        return user;
    }

    private String generateID() {
        return UUID.randomUUID().toString();
    }


}