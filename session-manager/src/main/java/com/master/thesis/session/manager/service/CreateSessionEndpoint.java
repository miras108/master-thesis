package com.master.thesis.session.manager.service;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import com.master.thesis.service.model.CreateSessionRQ;
import com.master.thesis.service.model.CreateSessionRS;
import com.master.thesis.service.model.ObjectFactory;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


@Endpoint
public class CreateSessionEndpoint {
	
	ObjectFactory objectFactory = new ObjectFactory();
    @PayloadRoot(namespace = "http://master.thesis.com/session-manager", localPart = "CreateSessionRequest")
    @ResponsePayload
    public JAXBElement<CreateSessionRS> searchProjects(@RequestPayload CreateSessionRQ request) {
     
        try {

            CreateSessionRS createSessionRS = objectFactory.createCreateSessionRS();
        	createSessionRS.setName(request.getName());
        	createSessionRS.setDepartment("MCA");
        	createSessionRS.setSubject1(request.getSubject1());
        	createSessionRS.setSubject2(request.getSubject2());
        	createSessionRS.setSubject3(request.getSubject3());
        	createSessionRS.setTotal(request.getSubject1() + request.getSubject2() + request.getSubject3());
 	    return new JAXBElement<CreateSessionRS>(new QName("http://master.thesis.com/session-manager",
               "createSessionResponse"), CreateSessionRS.class, createSessionRS);
        }
        catch (Exception e) {
           e.printStackTrace();
        }
        return null;
    }

}
