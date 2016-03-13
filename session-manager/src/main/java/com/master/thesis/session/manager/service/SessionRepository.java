package com.master.thesis.session.manager.service;

import com.master.thesis.service.model.Session;

import java.util.*;
import java.util.Map.Entry;

// TODO create tests
public class SessionRepository
{
    private int repositorySize = 10000;

    Map<String, Date> sessionIdCreationDateMap = new HashMap<String, Date>();

    public void addSession(Session session) {
        if (isSessionIdUnique(session.getId()))
        {
            addSessionToRepository(session);
            removeOldSessionIfOverloadedRepository();
        }
        else
        {
            //TODO Throw specific exception
            throw new RuntimeException();
        }
    }

    public boolean isSessionIdUnique(String sessionId)
    {
        return !sessionIdCreationDateMap.containsKey(sessionId);
    }

    private void addSessionToRepository(Session session)
    {
        sessionIdCreationDateMap.put(session.getId(), new Date());
    }

    private void removeOldSessionIfOverloadedRepository()
    {
        if(isRepositoryOverload())
        {
            String oldestSessionId = findOldestSessionId();
            removeSessionWithIdFromRepository(oldestSessionId);
        }
    }

    private boolean isRepositoryOverload()
    {
        return sessionIdCreationDateMap.size() > repositorySize;
    }

    private String findOldestSessionId()
    {
        Entry<String, Date> oldestSessionIdToDateEntry = null;
        for(Entry<String, Date> sessionIdEntry : sessionIdCreationDateMap.entrySet())
        {
            if(shouldReplaceOldestSessionIdEntry(oldestSessionIdToDateEntry, sessionIdEntry))
            {
                oldestSessionIdToDateEntry = sessionIdEntry;
            }
        }
        return oldestSessionIdToDateEntry.getKey();
    }

    private boolean shouldReplaceOldestSessionIdEntry(Entry<String, Date> oldestSessionIdToDateEntry, Entry<String, Date> sessionIdEntry) {
        return oldestSessionIdToDateEntry == null
                || sessionIdEntry.getValue().before(oldestSessionIdToDateEntry.getValue());
    }

    private void removeSessionWithIdFromRepository(String sessionId)
    {
        sessionIdCreationDateMap.remove(sessionId);
    }

    public void setRepositorySize(int repositorySize)
    {
        this.repositorySize = repositorySize;
    }
}
