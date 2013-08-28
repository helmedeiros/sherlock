package com.br.rbs.sherlock.user.repository;

import com.br.rbs.sherlock.user.domain.User;

import java.util.Map;

/**
 * .
 * User: helmedeiros
 * Date: 8/26/13
 * Time: 8:52 AM
 */
public interface UserRepository {
    public User save(final String customerName, final String user);

    public User find(final String user);

    public Map<String, User> findAll();

    public Map<String, User> findAllAnonymous();

    public boolean exist(final String userId);

    public User findAnonymousBySessionId(final String sessionId);

    public User saveAnonymous(String id);

    public User findAnonymousById(final String id);
}
