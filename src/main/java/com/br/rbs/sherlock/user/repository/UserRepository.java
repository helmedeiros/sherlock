package com.br.rbs.sherlock.user.repository;

import com.br.rbs.sherlock.api.exception.PersistenceException;
import com.br.rbs.sherlock.user.domain.entity.User;
import com.br.rbs.sherlock.user.domain.enums.Role;

import java.util.Collection;

/**
 * {@link User} repository contract.
 * User: helmedeiros
 * Date: 8/26/13
 * Time: 8:52 AM
 */
public interface UserRepository {
    public User update(final String customerName, final Integer id, Role role) throws PersistenceException;

    public User save(final String customerName, Role role) throws PersistenceException;

    public User find(final int id);

    public Collection<User> findAll();

    public <T> T findByExactField(Class<T> classe, String field, Object value);
}
