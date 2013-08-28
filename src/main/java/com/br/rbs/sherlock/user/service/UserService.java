package com.br.rbs.sherlock.user.service;

import com.br.rbs.sherlock.user.domain.User;

import java.util.Map;

/**
 * .
 * User: helmedeiros
 * Date: 8/25/13
 * Time: 10:10 PM
 */
public interface UserService {
    public User createUser(final String customerName, String user);

    public User findUser(final String userId);

    public Map<String, User> listUsers();

    public User createAnonymous(final String id);

    public Map<String, User> listAnonymousUsers();

    public User findAnonymousUser(final String anonymousId);
}
