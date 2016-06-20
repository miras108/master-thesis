package com.master.thesis.common.marschaller;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.codehaus.jettison.mapped.Configuration;
import org.codehaus.jettison.mapped.MappedNamespaceConvention;
import org.codehaus.jettison.mapped.MappedXMLStreamReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 * Created by miras108 on 2016-06-17.
 */
public class JsonUnmarschaller<Type> {

    final Class<Type> unmarschaledType;

    public JsonUnmarschaller(Class<Type> unmarschaledType) {
        this.unmarschaledType = unmarschaledType;
    }

    public Type unmarschall(String rawMessage) throws JAXBException, JSONException, XMLStreamException {
        JAXBContext jc = JAXBContext.newInstance(unmarschaledType);

        JSONObject obj = new JSONObject(rawMessage);
        Configuration config = new Configuration();
        MappedNamespaceConvention con = new MappedNamespaceConvention(config);
        XMLStreamReader xmlStreamReader = new MappedXMLStreamReader(obj, con);

        Unmarshaller unmarshaller = jc.createUnmarshaller();
        return  (Type) unmarshaller.unmarshal(xmlStreamReader);
    }
}
