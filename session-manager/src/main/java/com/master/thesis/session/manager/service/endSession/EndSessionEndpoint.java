package com.master.thesis.session.manager.service.endSession;

import com.master.thesis.service.model.EndSessionRQ;
import com.master.thesis.service.model.EndSessionRS;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.annotation.Resource;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;


@Endpoint
public class EndSessionEndpoint {

    private EndSessionResponseProcessor endSessionResponseProcessor;

    @PayloadRoot(namespace = "http://master.thesis.com/session-manager", localPart = "EndSessionRequest")
    @ResponsePayload
    public JAXBElement<EndSessionRS> endSession(@RequestPayload EndSessionRQ request) {

        EndSessionRS endSessionRS =  endSessionResponseProcessor.processResponse(request);

        return new JAXBElement<EndSessionRS>(new QName("http://master.thesis.com/session-manager",
                "endSessionResponse"), EndSessionRS.class, endSessionRS);
    }

    @Resource
    public void setEndSessionResponseProcessor(EndSessionResponseProcessor endSessionResponseProcessor) {
        this.endSessionResponseProcessor = endSessionResponseProcessor;
    }
}
