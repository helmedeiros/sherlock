package com.br.rbs.sherlock.session.repository;

import com.br.rbs.sherlock.api.util.ValidationUtil;
import com.br.rbs.sherlock.session.domain.Session;
import com.br.rbs.sherlock.user.domain.User;

import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

/**
 * .
 * User: helmedeiros
 * Date: 8/27/13
 * Time: 10:32 PM
 */
public class SessionRepositoryImpl implements SessionRepository {

    private Map<String, Session> sessions = new TreeMap<String, Session>();

    @Override
    public Session findOne(String sessionId) {
        return sessions.get(sessionId);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Session saveSession(String sessionId, User user) {
        Session session = findOne(sessionId);
        if(ValidationUtil.isEmpty(session)){

            final String id = generateID();

            session = new Session();
            session.setSessionId(id);
            session.setUserId(user.getUserId());

            sessions.put(id, session);
        }

        return session;
    }

    private String generateID() {
        return UUID.randomUUID().toString();
    }
}
