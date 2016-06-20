package com.master.thesis.common.marschaller;

import javax.xml.bind.*;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.io.StringWriter;

/**
 * Created by miras108 on 2016-06-20.
 */
public class XmlUnmarschaller<Type> {

    final Class<Type> unmarschaledType;

    public XmlUnmarschaller(Class<Type> unmarschaledType) {
        this.unmarschaledType = unmarschaledType;
    }

    public Type unmarschall(SOAPMessage soapMessage) throws JAXBException, SOAPException {
        JAXBContext jaxbContext = JAXBContext.newInstance(unmarschaledType);
        Unmarshaller um = jaxbContext.createUnmarshaller();

        // output pretty printed
        JAXBElement<Type> element = um.unmarshal(soapMessage.getSOAPBody(), unmarschaledType);
        return element.getValue();
    }
}
