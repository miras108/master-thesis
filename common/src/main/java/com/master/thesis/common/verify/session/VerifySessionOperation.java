package com.master.thesis.common.verify.session;

import com.master.thesis.service.model.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.soap.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Created by miras108 on 2016-06-18.
 */
public class VerifySessionOperation
{
    private String url = "http://localhost:8080/session-manager";
    private Integer serviceId;
    private String serviceName;

    public StatusCode verify(String sessionId) throws SOAPException, IOException, JAXBException {
        VerifySessionRQ verifySessionRQ = mapSessionRQ(sessionId);

        String rawRS = getRawRS(verifySessionRQ);
        SOAPMessage message = MessageFactory.newInstance().createMessage(null, new ByteArrayInputStream(rawRS.getBytes()));
        Unmarshaller unmarshaller = JAXBContext.newInstance(VerifySessionRS.class).createUnmarshaller();
        VerifySessionRS verifySessionRS = (VerifySessionRS) unmarshaller.unmarshal(message.getSOAPBody().extractContentAsDocument());

        verifySessionRS.getResponseStatus().getStatusCode();
        return StatusCode.valueOf("AAA");
    }

    private VerifySessionRQ mapSessionRQ(String sessionId) {
        Service service = new Service();
        service.setId(serviceId);
        service.setName(serviceName);

        Session session = new Session();
        session.setId(sessionId);

        VerifySessionRQ verifySessionRQ = new VerifySessionRQ();
        verifySessionRQ.setService(service);
        verifySessionRQ.setSession(session);
        return verifySessionRQ;
    }

    private String getRawRS(VerifySessionRQ verifySessionRQ) throws SOAPException {
        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
        SOAPConnection soapConnection = soapConnectionFactory.createConnection();

        SOAPMessage soapResponse = soapConnection.call(createSoapRequest(verifySessionRQ), url);
//        return soapResponse.writeTo();
        return null;
    }

    private SOAPMessage createSoapRequest(VerifySessionRQ verifySessionRQ) throws SOAPException {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();
        return  soapMessage;
    }

    ;
}
