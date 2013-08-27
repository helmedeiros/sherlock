package com.br.rbs.sherlock.user.data;

import com.br.rbs.sherlock.user.domain.User;

import java.util.Map;

/**
 * .
 * User: helmedeiros
 * Date: 8/26/13
 * Time: 8:52 AM
 */
public interface UserDAO {
    public User save(final String customerName, final String user);

    public User find(final String user);

    public Map<String, User> findAll();

    public User save(final String sessionId);

    public Map<String, User> findAllAnonymous();

    public boolean exist(final String userId);

    public User findAnonymous(final String sessionId);
}
