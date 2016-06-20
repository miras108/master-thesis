package com.master.thesis.service.model.json;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by miras108 on 2016-06-17.
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class JsonResponseRoot<RS> {

    @XmlElement(name = "response")
    private RS response;

    public JsonResponseRoot(RS response) {
        this.response = response;
    }

    public RS getResponse() {
        return response;
    }

    public void setResponse(RS response) {
        this.response = response;
    }
}
