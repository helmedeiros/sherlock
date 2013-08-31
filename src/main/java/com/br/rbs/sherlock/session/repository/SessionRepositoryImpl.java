package com.br.rbs.sherlock.session.repository;

import com.br.rbs.sherlock.api.domain.enums.PERSIST_OPTION;
import com.br.rbs.sherlock.api.exception.PersistenceException;
import com.br.rbs.sherlock.api.repository.GenericRepositoryImpl;
import com.br.rbs.sherlock.api.util.ValidationUtil;
import com.br.rbs.sherlock.session.domain.Session;

import java.util.*;

/**
 * Data access class to {@link Session}.
 * User: helmedeiros
 * Date: 8/27/13
 * Time: 10:32 PM
 */
public class SessionRepositoryImpl extends GenericRepositoryImpl implements SessionRepository {

    public SessionRepositoryImpl() {
        super();
    }

    public Session update(Session session, final String sessionId) throws PersistenceException {
        Session newSession = null;

        if (!ValidationUtil.isEmpty(sessionId)){
            newSession = findByExactField(Session.class, "sessionId", sessionId);
        }

        if(ValidationUtil.isEmpty(newSession)) {
            newSession = new Session();
            newSession.setSessionId(generateID());
            newSession.setActive(true);
            newSession.setUserId(session.getUserId());
            newSession.setCreateTime(new Date());
            newSession.setLastAccessedTime(new Date());

            changeOperation(session, PERSIST_OPTION.INSERT);
        }

        session = find(newSession.getId());

        return session;
    }

    @Override
    public Session save(final Session session) throws PersistenceException {
        return update(session, null);
    }

    public Session find(int id) {
        Session session = null;

        try {
            session = findByPrimaryKey(id, Session.class);
        } catch (PersistenceException e) {
            e.printStackTrace();
        }

        return session;
    }

    private String generateID() {
        return UUID.randomUUID().toString();
    }
}
