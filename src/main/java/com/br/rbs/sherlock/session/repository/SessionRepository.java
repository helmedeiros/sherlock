package com.br.rbs.sherlock.session.repository;

import com.br.rbs.sherlock.api.exception.PersistenceException;
import com.br.rbs.sherlock.session.domain.Session;

/**
 * {@link Session} repository contract.
 * User: helmedeiros
 * Date: 8/27/13
 * Time: 10:32 PM
 */
public interface SessionRepository {
    public Session update(final Session session, final String id) throws PersistenceException;

    public Session save(final Session session) throws PersistenceException;

    public Session find(final int id);

    public <T> T findByExactField(Class<T> classe, String field, Object value);
}
