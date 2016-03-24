package com.master.thesis.session.manager.service.session.repository;

import com.master.thesis.service.model.Session;

import java.util.Date;

/**
 * Created by miras108 on 2016-03-24.
 */
public class SessionWrapper
{
    private String sessionId;
    private Session session;
    private Date creationDate;

    public SessionWrapper(String sessionId, Session session)
    {
        this.sessionId = sessionId;
        this.session = session;
        this.creationDate = new Date();
    }

    public SessionWrapper(String sessionId)
    {
        this.sessionId = sessionId;
    }

    public Session getSession() {
        return session;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SessionWrapper that = (SessionWrapper) o;

        return !(sessionId != null ? !sessionId.equals(that.sessionId) : that.sessionId != null);

    }

    @Override
    public int hashCode() {
        return sessionId != null ? sessionId.hashCode() : 0;
    }
}
