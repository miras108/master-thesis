package com.master.thesis.currency.downline.bindings;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by miras108 on 2016-06-14.
 */
@XmlRootElement
public class Rates {

    private Double PLN;
    private Double EUR;
    private Double CHF;
    private Double USD;
    private Double GBP;

    public Rates() {
    }

    public Double getPLN() {
        return PLN;
    }

    public void setPLN(Double PLN) {
        this.PLN = PLN;
    }

    public Double getEUR() {
        return EUR;
    }

    public void setEUR(Double EUR) {
        this.EUR = EUR;
    }

    public Double getCHF() {
        return CHF;
    }

    public void setCHF(Double CHF) {
        this.CHF = CHF;
    }

    public Double getUSD() {
        return USD;
    }

    public void setUSD(Double USD) {
        this.USD = USD;
    }

    public Double getGBP() {
        return GBP;
    }

    public void setGBP(Double GBP) {
        this.GBP = GBP;
    }
}
