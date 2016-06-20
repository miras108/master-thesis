package com.master.thesis.currency.service;

import com.master.thesis.common.service.operation.rest.JsonRestOperation;
import com.master.thesis.common.service.operation.rest.RequestParameter;

import java.util.List;

/**
 * Created by miras108 on 2016-06-18.
 */
public class JsonCurrencyRestOperationAdapter extends JsonRestOperation {
    @Override
    public String call(List<RequestParameter> requestParameters) {

        String response = super.call(requestParameters);
        return "{\"currency\":" + response + "}";
    }
}
