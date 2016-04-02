package com.master.thesis.session.manager.service.verifySession;

import com.master.thesis.data.source.entity.*;
import com.master.thesis.data.source.entity.Privilege;
import com.master.thesis.service.model.*;
import com.master.thesis.service.model.Service;
import com.master.thesis.session.manager.service.SessionPrivilegeResolver;
import com.master.thesis.session.manager.service.exception.InsufficientPrivilegesException;
import com.master.thesis.session.manager.service.session.repository.SessionRepository;

/**
 * Created by miras108 on 2016-02-28.
 */
public class VerifySessionResponseProcessor {
    private SessionPrivilegeResolver sessionPrivilegeResolver;
    private SessionRepository sessionRepository;

    public VerifySessionRS processResponse(VerifySessionRQ verifySessionRQ) {
        VerifySessionRS verifySessionRS = createResponseAndPopulateDataFromRequest(verifySessionRQ);
        try {
            verifyAccessServiceToSession(verifySessionRQ);
            verifySessionRS = includeCorrectStatusInResponse(verifySessionRS);
        } catch (InsufficientPrivilegesException e) {
            verifySessionRS = includeErrorStatusInResponse(verifySessionRS, e);
        }
        return verifySessionRS;
    }

    private VerifySessionRS createResponseAndPopulateDataFromRequest(VerifySessionRQ verifySessionRQ) {
        VerifySessionRS verifySessionRS = new VerifySessionRS();
        verifySessionRS.setService(verifySessionRQ.getService());
        verifySessionRS.setSession(verifySessionRQ.getSession());

        return verifySessionRS;
    }


    private void verifyAccessServiceToSession(VerifySessionRQ verifySessionRQ) {
        Session sessionFromRepository = sessionRepository.getSessionFromRepository(verifySessionRQ.getSession().getId());
        if (sessionFromRepository == null || !sessionPrivilegeResolver.hasServicePrivilegeToSession(verifySessionRQ.getService(), sessionFromRepository)) {
            // TODO change to specific exception
            throw new InsufficientPrivilegesException(Privilege.OFFERS);
        }
    }

    private VerifySessionRS includeCorrectStatusInResponse(VerifySessionRS verifySessionRS) {
        ResponseStatus responseStatus = new ResponseStatus();
        responseStatus.setStatusCode(StatusCode.CORRECT);
        verifySessionRS.setResponseStatus(responseStatus);
        return verifySessionRS;
    }

    private VerifySessionRS includeErrorStatusInResponse(VerifySessionRS verifySessionRS, Exception exception) {
        ResponseStatus responseStatus = new ResponseStatus();
        responseStatus.setStatusCode(StatusCode.ERROR);
        responseStatus.setMessage(exception.getMessage());
        verifySessionRS.setResponseStatus(responseStatus);
        return verifySessionRS;
    }

    public void setSessionPrivilegeResolver(SessionPrivilegeResolver sessionPrivilegeResolver) {
        this.sessionPrivilegeResolver = sessionPrivilegeResolver;
    }

    public void setSessionRepository(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }
}
