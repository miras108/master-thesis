package com.master.thesis.session.manager.service.createSession;

import com.master.thesis.service.model.*;

/**
 * Created by miras108 on 2016-02-28.
 */
public class CreateSessionResponseProcessor
{
    public CreateSessionRS processCorrectResponse(CreateSessionRQ createSessionRQ, Session session)
    {
        CreateSessionRS createSessionRS = createResponseAndPopulateDataFromRequest(createSessionRQ);
        createSessionRS = includeSessionInResponse(createSessionRS, session);
        return includeCorrectStatusInResponse(createSessionRS);
    }

    public CreateSessionRS processInvalidResponse(CreateSessionRQ createSessionRQ, Exception exception)
    {
        CreateSessionRS createSessionRS = createResponseAndPopulateDataFromRequest(createSessionRQ);
        return includeErrorStatusInResponse(createSessionRS, exception);
    }

    private CreateSessionRS createResponseAndPopulateDataFromRequest(CreateSessionRQ createSessionRQ)
    {
        CreateSessionRS createSessionRS = new CreateSessionRS();
        createSessionRS.setRequestNumber(createSessionRQ.getRequestNumber());
        createSessionRS.setService(createSessionRQ.getService());

        return createSessionRS;
    }

    private CreateSessionRS includeSessionInResponse(CreateSessionRS createSessionRS, Session session)
    {
        createSessionRS.setSession(session);
        return createSessionRS;
    }

    private CreateSessionRS includeCorrectStatusInResponse(CreateSessionRS createSessionRS)
    {
        ResponseStatus responseStatus = new ResponseStatus();
        responseStatus.setStatusCode(StatusCode.CORRECT);
        createSessionRS.setResponseStatus(responseStatus);
        return createSessionRS;
    }

    private CreateSessionRS includeErrorStatusInResponse(CreateSessionRS createSessionRS, Exception exception)
    {
        ResponseStatus responseStatus =  new ResponseStatus();
        responseStatus.setStatusCode(StatusCode.ERROR);
        responseStatus.setMessage(exception.getMessage());
        createSessionRS.setResponseStatus(responseStatus);
        return createSessionRS;
    }
}
