package com.br.rbs.sherlock.user.repository;

import com.br.rbs.sherlock.api.domain.enums.PERSIST_OPTION;
import com.br.rbs.sherlock.api.exception.PersistenceException;
import com.br.rbs.sherlock.api.repository.GenericRepositoryImpl;
import com.br.rbs.sherlock.api.util.ValidationUtil;
import com.br.rbs.sherlock.user.domain.entity.User;
import com.br.rbs.sherlock.user.domain.enums.Role;

import java.util.*;

public class UserRepositoryImpl extends GenericRepositoryImpl implements UserRepository {

    public UserRepositoryImpl() {
        super();
    }

    public User update(final String customerName, final Integer id, final Role role) throws PersistenceException {
        User user = null;

        if (!ValidationUtil.isEmpty(id)){
            user = find(id);
        }

        if(ValidationUtil.isEmpty(user)) {
            user = new User();

            user.setName(customerName);
            user.setUserId(generateID());
            user.setRole(role);

            changeOperation(user, PERSIST_OPTION.INSERT);
        }

        user = find(user.getId());

        return user;
    }

    @Override
    public User save(final String customerName, Role role) throws PersistenceException {
        return update(customerName, null, role);
    }

    public User find(int id) {
        User user = null;

        try {
            user = findByPrimaryKey(id, User.class);
        } catch (PersistenceException e) {
            e.printStackTrace();
        }

        return user;
    }

    public Collection<User> findAll() {
        Collection<User> users = null;

        try {
            users = findAll(User.class);
        } catch (PersistenceException e) {
            e.printStackTrace();
        }

        return users;
    }

    private String generateID() {
        return UUID.randomUUID().toString();
    }


}