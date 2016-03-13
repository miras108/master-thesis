package com.master.thesis.session.manager.service;

import com.master.thesis.service.model.CreateSessionRQ;
import com.master.thesis.service.model.CreateSessionRS;
import com.master.thesis.service.model.Session;

/**
 * Created by miras108 on 2016-02-28.
 */
public class CreateSessionResponseProcessor
{
    public CreateSessionRS processCorrectResponse(CreateSessionRQ createSessionRQ, Session session)
    {
        CreateSessionRS createSessionRS = createResponseAndPopulateDataFromRequest(createSessionRQ);
        return includeSessiontoResponse(createSessionRS, session);
    }

    private CreateSessionRS createResponseAndPopulateDataFromRequest(CreateSessionRQ createSessionRQ)
    {
        CreateSessionRS createSessionRS = new CreateSessionRS();
        createSessionRS.setRequestNumber(createSessionRQ.getRequestNumber());
        createSessionRS.setService(createSessionRQ.getService());
        createSessionRS.setPrivileges(createSessionRQ.getPrivileges());

        return createSessionRS;
    }

    private CreateSessionRS includeSessiontoResponse(CreateSessionRS createSessionRS, Session session)
    {
        createSessionRS.setSession(session);
        return createSessionRS;
    }
}
