package com.br.rbs.sherlock.user.service;

import com.br.rbs.sherlock.user.domain.entity.User;

import java.util.Collection;

/**
 * .
 * User: helmedeiros
 * Date: 8/25/13
 * Time: 10:10 PM
 */
public interface UserService {
    public User createUser(final String customerName, final String sessionId);

    public User findUser(final int userId);

    public Collection<User> listUsers();
}
