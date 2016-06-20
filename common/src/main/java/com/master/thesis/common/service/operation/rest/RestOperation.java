package com.master.thesis.common.service.operation.rest;

import java.util.List;

/**
 * Created by miras108 on 2016-06-17.
 */
public interface RestOperation {
    String call(List<RequestParameter> requestParameters);
}
