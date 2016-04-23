package com.master.thesis.session.manager.service.endSession;

import com.master.thesis.data.source.entity.Privilege;
import com.master.thesis.service.model.*;
import com.master.thesis.session.manager.service.SessionPrivilegeResolver;
import com.master.thesis.session.manager.service.exception.InsufficientPrivilegesException;
import com.master.thesis.session.manager.service.session.repository.SessionRepository;

/**
 * Created by miras108 on 2016-02-28.
 */
public class EndSessionResponseProcessor {
    private SessionPrivilegeResolver sessionPrivilegeResolver;
    private SessionRepository sessionRepository;

    public EndSessionRS processResponse(EndSessionRQ endSessionRQ) {
        EndSessionRS endSessionRS = createResponseAndPopulateDataFromRequest(endSessionRQ);
        try {
            verifyServiceHasPrvilegeToCloseSession(endSessionRQ);
            sessionRepository.removeSession(endSessionRQ.getSession());
            endSessionRS = includeCloseStatusInResponse(endSessionRS);
        } catch (InsufficientPrivilegesException e) {
            endSessionRS = includeErrorStatusInResponse(endSessionRS, e);
        }
        return endSessionRS;
    }

    private EndSessionRS createResponseAndPopulateDataFromRequest(EndSessionRQ endSessionRQ) {
        EndSessionRS endSessionRS = new EndSessionRS();
        endSessionRS.setService(endSessionRQ.getService());
        endSessionRS.setSession(endSessionRQ.getSession());

        return endSessionRS;
    }


    private void verifyServiceHasPrvilegeToCloseSession(EndSessionRQ endSessionRQ) {
        Session sessionFromRepository = sessionRepository.getSessionFromRepository(endSessionRQ.getSession().getId());
        if (sessionFromRepository == null || !sessionPrivilegeResolver.hasServicePrivilegeToManageSession(endSessionRQ.getService())) {
            throw new InsufficientPrivilegesException(Privilege.MANAGE_SESSION);
        }
    }

    private EndSessionRS includeCloseStatusInResponse(EndSessionRS endSessionRS) {
        ResponseStatus responseStatus = new ResponseStatus();
        responseStatus.setStatusCode(StatusCode.CLOSED);
        endSessionRS.setResponseStatus(responseStatus);
        return endSessionRS;
    }

    private EndSessionRS includeErrorStatusInResponse(EndSessionRS endSessionRS, Exception exception) {
        ResponseStatus responseStatus = new ResponseStatus();
        responseStatus.setStatusCode(StatusCode.ERROR);
        responseStatus.setMessage(exception.getMessage());
        endSessionRS.setResponseStatus(responseStatus);
        return endSessionRS;
    }

    public void setSessionPrivilegeResolver(SessionPrivilegeResolver sessionPrivilegeResolver) {
        this.sessionPrivilegeResolver = sessionPrivilegeResolver;
    }

    public void setSessionRepository(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }
}
