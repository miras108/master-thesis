package com.master.thesis.service.model.json.currency;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by miras108 on 2016-06-17.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class CurrencyServiceRS {

    @XmlElement(name = "baseCurrency")
    private String baseCurrency;

    @XmlElement(name = "baseValue")
    private Double baseValue;

    @XmlElement(name = "exchangedCurrency")
    private String exchangedCurrency;

    @XmlElement(name = "exchangedValue")
    private Double exchangedValue;

    @XmlElement(name = "exchangeRatio")
    private Double exchangeRatio;

    public CurrencyServiceRS() {
    }

    public CurrencyServiceRS(String baseCurrency, Double baseValue, String exchangedCurrency, Double exchangedValue, Double exchangeRatio) {
        this.baseCurrency = baseCurrency;
        this.baseValue = baseValue;
        this.exchangedCurrency = exchangedCurrency;
        this.exchangedValue = exchangedValue;
        this.exchangeRatio = exchangeRatio;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public Double getBaseValue() {
        return baseValue;
    }

    public void setBaseValue(Double baseValue) {
        this.baseValue = baseValue;
    }

    public String getExchangedCurrency() {
        return exchangedCurrency;
    }

    public void setExchangedCurrency(String exchangedCurrency) {
        this.exchangedCurrency = exchangedCurrency;
    }

    public Double getExchangedValue() {
        return exchangedValue;
    }

    public void setExchangedValue(Double exchangedValue) {
        this.exchangedValue = exchangedValue;
    }

    public Double getExchangeRatio() {
        return exchangeRatio;
    }

    public void setExchangeRatio(Double exchangeRatio) {
        this.exchangeRatio = exchangeRatio;
    }
}
