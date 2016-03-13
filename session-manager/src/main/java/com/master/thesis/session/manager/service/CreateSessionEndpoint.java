package com.master.thesis.session.manager.service;

import com.master.thesis.data.source.dao.ServiceDao;
import com.master.thesis.data.source.entity.Service;
import com.master.thesis.service.model.CreateSessionRQ;
import com.master.thesis.service.model.CreateSessionRS;
import com.master.thesis.service.model.ObjectFactory;
import com.master.thesis.service.model.Session;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.annotation.Resource;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;


@Endpoint
public class CreateSessionEndpoint {

    CreateSessionCommand createSessionCommand;
    CreateSessionResponseProcessor createSessionResponseProcessor;

    @PayloadRoot(namespace = "http://master.thesis.com/session-manager", localPart = "CreateSessionRequest")
    @ResponsePayload
    public JAXBElement<CreateSessionRS> searchProjects(@RequestPayload CreateSessionRQ request) {

        try {
            Session session = createSessionCommand.execute(request);
            CreateSessionRS createSessionRS = createSessionResponseProcessor.processCorrectResponse(request, session);

            return new JAXBElement<CreateSessionRS>(new QName("http://master.thesis.com/session-manager",
                    "createSessionResponse"), CreateSessionRS.class, createSessionRS);
        }
        // TODO catch exceptions
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Resource
    public void setCreateSessionCommand(CreateSessionCommand createSessionCommand) {
        this.createSessionCommand = createSessionCommand;
    }

    @Resource
    public void setCreateSessionResponseProcessor(CreateSessionResponseProcessor createSessionResponseProcessor) {
        this.createSessionResponseProcessor = createSessionResponseProcessor;
    }
}
