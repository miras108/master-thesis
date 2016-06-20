package com.master.thesis.common.service.operation.soap;

/**
 * Created by miras108 on 2016-06-20.
 */
public interface SoapOperation<RS,RQ> {
    RS call(RQ request);
}
