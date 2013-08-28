package com.br.rbs.sherlock.session.repository;

import com.br.rbs.sherlock.session.domain.Session;
import com.br.rbs.sherlock.user.domain.User;

/**
 * .
 * User: helmedeiros
 * Date: 8/27/13
 * Time: 10:32 PM
 */
public interface SessionRepository {
    public Session findOne(final String sessionId);

    public Session saveSession(final String sessionId, final User user);
}
