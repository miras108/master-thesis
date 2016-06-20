package com.master.thesis.common.service.operation.soap;

import com.master.thesis.common.marschaller.XmlMarschaller;
import com.master.thesis.common.marschaller.XmlUnmarschaller;

import javax.xml.soap.*;

/**
 * Created by miras108 on 2016-06-20.
 */
public class XmlSoapOperation<RS,RQ>  {

    private final Class<RQ> rqType;
    private final Class<RS> rsType;

    private String url;

    public XmlSoapOperation(Class<RQ> rqType, Class<RS> rsType) {
        this.rqType = rqType;
        this.rsType = rsType;
    }

    public RS call(RQ request) throws Exception {
        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
        SOAPConnection soapConnection = soapConnectionFactory.createConnection();

        SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(request), url);

        XmlUnmarschaller<RS> rsXmlUnmarschaller = new XmlUnmarschaller<>(rsType);

        return rsXmlUnmarschaller.unmarschall(soapResponse);
    }

    private SOAPMessage createSOAPRequest(RQ request) throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();


        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration("validateSession", url);

        // SOAP Body
        SOAPBody soapBody = envelope.getBody();

        XmlMarschaller<RQ> rqXmlMarschaller = new XmlMarschaller<>(rqType);
        soapBody.setValue(rqXmlMarschaller.unmarschall(request));

        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", url  + "VerifySession");

        soapMessage.saveChanges();

        /* Print the request message */
        System.out.print("Request SOAP Message:");
        soapMessage.writeTo(System.out);
        System.out.println();

        return soapMessage;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
