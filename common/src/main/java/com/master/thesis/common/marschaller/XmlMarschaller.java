package com.master.thesis.common.marschaller;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

/**
 * Created by miras108 on 2016-06-20.
 */
public class XmlMarschaller<Type> {

    final Class<Type> marschaledType;

    public XmlMarschaller(Class<Type> marschaledType) {
        this.marschaledType = marschaledType;
    }

    public String unmarschall(Type objectMessage) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(marschaledType);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        // output pretty printed
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        java.io.StringWriter sw = new StringWriter();

        jaxbMarshaller.marshal(objectMessage, sw);

        return sw.toString();
    }
}
