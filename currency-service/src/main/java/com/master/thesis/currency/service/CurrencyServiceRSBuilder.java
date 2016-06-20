package com.master.thesis.currency.service;

import com.master.thesis.currency.downline.bindings.Currency;
import com.master.thesis.service.model.json.currency.CurrencyServiceRS;

/**
 * Created by miras108 on 2016-06-20.
 */
public class CurrencyServiceRSBuilder {
    private Currency currency;
    private Double baseValue;
    private String exchangedCurrency;

    public CurrencyServiceRS build()
    {
        Double exchangeRatio = resolveRequestedCurrencyRatio(currency, exchangedCurrency);

        CurrencyServiceRS currencyServiceRS = new CurrencyServiceRS();
        currencyServiceRS.setBaseCurrency(currency.getBase());
        currencyServiceRS.setExchangedCurrency(exchangedCurrency);
        currencyServiceRS.setBaseValue(baseValue);
        currencyServiceRS.setExchangeRatio(exchangeRatio);
        currencyServiceRS.setExchangedValue(calculateExchangedValue(exchangeRatio));
        return currencyServiceRS;
    }

    private Double calculateExchangedValue(Double exchangeRatio) {
        return exchangeRatio * baseValue;
    }

    private Double resolveRequestedCurrencyRatio(Currency currency, String requestedCurrency) {
        CurrencyCode requestedCurrencyCode = CurrencyCode.valueOf(requestedCurrency);

        switch (requestedCurrencyCode) {
            case CHF:
                return currency.getRates().getCHF();
            case EUR:
                return currency.getRates().getEUR();
            case GBP:
                return currency.getRates().getGBP();
            case PLN:
                return currency.getRates().getPLN();
            case USD:
                return currency.getRates().getUSD();
            default:
                return null;
        }
    }

    public CurrencyServiceRSBuilder withCurrency(Currency currency)
    {
        this.currency = currency;
        return this;
    }

    public CurrencyServiceRSBuilder withBaseValue(Double baseValue)
    {
        this.baseValue = baseValue;
        return this;
    }

    public CurrencyServiceRSBuilder withExchangedCurrency(String exchangedCurrency)
    {
        this.exchangedCurrency = exchangedCurrency;
        return this;
    }

}
