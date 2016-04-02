package com.master.thesis.session.manager.service.createSession;

import com.master.thesis.data.source.entity.Privilege;
import com.master.thesis.service.model.CreateSessionRQ;
import com.master.thesis.service.model.Service;
import com.master.thesis.service.model.Session;
import com.master.thesis.session.manager.service.SessionPrivilegeResolver;
import com.master.thesis.session.manager.service.exception.InsufficientPrivilegesException;

/**
 * Created by miras108 on 2016-02-28.
 */
public class CreateSessionCommand
{
    private SessionPrivilegeResolver sessionPrivilegeResolver;
    private SessionCreator sessionCreator;

    public Session execute(CreateSessionRQ createSessionRQ)
    {
        if(hasServicePrivilegeToCreateSession(createSessionRQ.getService()))
        {
            return sessionCreator.createUniqueSession(createSessionRQ.getPrivileges());
        }
        throw new InsufficientPrivilegesException(Privilege.MANAGE_SESSION);
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
