package com.br.rbs.sherlock.user.data;

import com.br.rbs.sherlock.api.util.ValidationUtil;
import com.br.rbs.sherlock.user.domain.User;

import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

public class UserDAOImpl implements UserDAO {
    public static Map<String, User> users = new TreeMap<String, User>();
    public static Map<String, User> anonymousUsers = new TreeMap<String, User>();

    public UserDAOImpl() {
    }

    public User save(final String customerName, final String id) {
        //Verify if the user id is from anonymous or not.
        User user = findAnonymousById(id);

        if(ValidationUtil.isEmpty(id) || !ValidationUtil.isEmpty(user)){
            user.setName(customerName);
            user.setAnonymous(id);
            user.setId(generateID());

            users.put(user.getId(), user);
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
            if (cod.getValue().getId().equals(id)){
                user = cod.getValue();
            }
        }
        return user;
    }

    @Override
    public User saveAnonymous(final String id) {
        User anonymousUser = findAnonymousById(id);

        if(ValidationUtil.isEmpty(anonymousUser)){
            final String newId = generateID();

            if(ValidationUtil.isEmpty(id) || !anonymousUsers.containsKey(id)){
                User user = new User();
                user.setId(newId);
                user.setAnonymous(newId);

                anonymousUsers.put(newId, user);
            }

            anonymousUser = anonymousUsers.get(newId);
        }

        return anonymousUser;
    }

    private String generateID() {
        return UUID.randomUUID().toString();
    }


}