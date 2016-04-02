package com.master.thesis.session.manager.service.verifySession;

import com.master.thesis.service.model.*;
import com.master.thesis.session.manager.service.createSession.CreateSessionCommand;
import com.master.thesis.session.manager.service.createSession.CreateSessionResponseProcessor;
import com.master.thesis.session.manager.service.exception.InsufficientPrivilegesException;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.annotation.Resource;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;


@Endpoint
public class VerifySessionEndpoint {

    VerifySessionResponseProcessor verifySessionResponseProcessor;

    @PayloadRoot(namespace = "http://master.thesis.com/session-manager", localPart = "VerifySessionRequest")
    @ResponsePayload
    public JAXBElement<VerifySessionRS> verifySession(@RequestPayload VerifySessionRQ request) {
        VerifySessionRS verifySessionRS = verifySessionResponseProcessor.processResponse(request);

        return new JAXBElement<VerifySessionRS>(new QName("http://master.thesis.com/session-manager",
                "verifySessionResponse"), VerifySessionRS.class, verifySessionRS);
    }

    @Resource
    public void setVerifySessionResponseProcessor(VerifySessionResponseProcessor verifySessionResponseProcessor) {
        this.verifySessionResponseProcessor = verifySessionResponseProcessor;
    }
}
