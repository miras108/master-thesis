package com.master.thesis.session.manager.service.createSession;

import com.master.thesis.service.model.Privileges;
import com.master.thesis.service.model.Session;
import com.master.thesis.session.manager.service.session.repository.SessionRepository;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Created by miras108 on 2016-02-28.
 */
public class SessionCreator
{
    private SessionRepository sessionRepository;

    private SecureRandom random = new SecureRandom();

    public Session createUniqueSession(Privileges privileges)
    {
        Session session = new Session();
        session.setId(getUniqueSessionId());
        session.setPrivileges(privileges);
        addSessionToRepository(session);

        return session;
    }

    private String getUniqueSessionId()
    {
        String sessionId = null;
        do {
            sessionId = generateRandomSessionId();
        } while(!sessionRepository.isSessionIdUnique(sessionId));

        return sessionId;
    }

    private void addSessionToRepository(Session session) {
        sessionRepository.addSession(session);
    }

    public String generateRandomSessionId()
    {
        return new BigInteger(130, random).toString(32);
    }

    public void setSessionRepository(SessionRepository sessionRepository)
    {
        this.sessionRepository = sessionRepository;
    }
}
