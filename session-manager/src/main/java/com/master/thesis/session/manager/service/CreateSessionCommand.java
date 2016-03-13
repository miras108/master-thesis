package com.master.thesis.session.manager.service;

import com.master.thesis.service.model.CreateSessionRQ;
import com.master.thesis.service.model.Service;
import com.master.thesis.service.model.Session;

/**
 * Created by miras108 on 2016-02-28.
 */
public class CreateSessionCommand
{
    SessionPrivilegeResolver sessionPrivilegeResolver;
    SessionCreator sessionCreator;

    public Session execute(CreateSessionRQ createSessionRQ)
    {
        if(hasServicePrivilegeToCreateSession(createSessionRQ.getService()))
        {
            return sessionCreator.createUniqueSession();
        }
        // TODO throw specific exception
        throw new RuntimeException();
    }

    private boolean hasServicePrivilegeToCreateSession(Service service)
    {
        return sessionPrivilegeResolver.hasServicePrivilegeToCreateSession(service);
    }

    public void setSessionPrivilegeResolver(SessionPrivilegeResolver sessionPrivilegeResolver)
    {
        this.sessionPrivilegeResolver = sessionPrivilegeResolver;
    }

    public void setSessionCreator(SessionCreator sessionCreator)
    {
        this.sessionCreator = sessionCreator;
    }
}
