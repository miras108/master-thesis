package com.master.thesis.currency.downline.bindings;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by miras108 on 2016-04-23.
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Currency {

    @XmlElement(name = "base")
    private String base;

    @XmlElement(name = "date")
    private String date;

    @XmlElement(name = "rates")
    private Rates rates;

    public Currency() {
    }

    public Currency(String base, String date, Rates rates) {
        this.base = base;
        this.date = date;
        this.rates = rates;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Rates getRates() {
        return rates;
    }

    public void setRates(Rates rates) {
        this.rates = rates;
    }
}