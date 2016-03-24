package com.master.thesis.session.manager.service.session.repository;

import com.master.thesis.service.model.Session;

import java.util.HashSet;
import java.util.Set;

// TODO create tests
public class SessionRepository
{
    private int repositorySize = 10000;

    Set<SessionWrapper> repository = new HashSet();

    public void addSession(Session session) {
            addSessionToRepository(session);
            removeOldSessionIfOverloadedRepository();
    }

    public boolean isSessionIdUnique(String sessionId)
    {
        return !repository.contains(new SessionWrapper(sessionId));
    }

    private void addSessionToRepository(Session session)
    {
        SessionWrapper sessionWrapper = new SessionWrapper(session.getId(), session);
        repository.add(sessionWrapper);
    }

    private void removeOldSessionIfOverloadedRepository()
    {
        if(isRepositoryOverload())
        {
            SessionWrapper oldestSession = findOldestSession();
            removeSessionWithIdFromRepository(oldestSession);
        }
    }

    private boolean isRepositoryOverload()
    {
        return repository.size() > repositorySize;
    }

    private SessionWrapper findOldestSession()
    {
        SessionWrapper oldestSession = null;
        for(SessionWrapper sessionWrapper : repository)
        {
            if(shouldReplaceOldestSessionIdEntry(oldestSession, sessionWrapper))
            {
                oldestSession = sessionWrapper;
            }
        }
        return oldestSession;
    }

    private boolean shouldReplaceOldestSessionIdEntry(SessionWrapper oldestSession, SessionWrapper sessionWrapper) {
        return oldestSession == null
                || sessionWrapper.getCreationDate().before(oldestSession.getCreationDate());
    }

    private void removeSessionWithIdFromRepository(SessionWrapper session)
    {
        repository.remove(session);
    }

    public void setRepositorySize(int repositorySize)
    {
        this.repositorySize = repositorySize;
    }
}
