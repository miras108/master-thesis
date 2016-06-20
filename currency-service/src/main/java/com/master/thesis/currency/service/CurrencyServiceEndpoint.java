package com.master.thesis.currency.service;

import com.master.thesis.common.marschaller.JsonUnmarschaller;
import com.master.thesis.common.service.operation.rest.RestOperation;
import com.master.thesis.common.service.operation.rest.RequestParameter;
import com.master.thesis.currency.downline.bindings.Currency;
import com.master.thesis.service.model.json.JsonResponseRoot;
import com.master.thesis.service.model.json.currency.CurrencyServiceRS;
import org.codehaus.jettison.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * Created by miras108 on 2016-04-23.
 */

@RestController
public class CurrencyServiceEndpoint {

    public static final String BASE_PARAMETER = "base";
    private JsonUnmarschaller<Currency> currencyUnmarschaller;
    private RestOperation restOperation;

    @RequestMapping(value = "/exchangeCurrency", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<JsonResponseRoot> exchangeCurrency(
            @RequestParam("baseCurrency") String baseCurrency,
            @RequestParam("baseValue") Double baseValue,
            @RequestParam("requestedCurrency") String requestedCurrency)
            throws JAXBException, JSONException, XMLStreamException
    {
        String response = restOperation.call(createParameters(baseCurrency));
        Currency currency = currencyUnmarschaller.unmarschall(response);

        CurrencyServiceRS currencyServiceRS = new CurrencyServiceRSBuilder()
                .withCurrency(currency)
                .withExchangedCurrency(requestedCurrency)
                .withBaseValue(baseValue)
                .build();

        return new ResponseEntity<>(new JsonResponseRoot<>(currencyServiceRS), HttpStatus.OK);
    }

    private List<RequestParameter> createParameters(String baseCurrency) {
        RequestParameter baseParameter = new RequestParameter();
        baseParameter.setParameter(BASE_PARAMETER);
        baseParameter.setParameterValue(baseCurrency);
        return asList(baseParameter);
    }

    @Resource
    public void setCurrencyUnmarschaller(JsonUnmarschaller<Currency> currencyUnmarschaller) {
        this.currencyUnmarschaller = currencyUnmarschaller;
    }

    @Resource(type = JsonCurrencyRestOperationAdapter.class)
    public void setRestOperation(RestOperation restOperation) {
        this.restOperation = restOperation;
    }
}
