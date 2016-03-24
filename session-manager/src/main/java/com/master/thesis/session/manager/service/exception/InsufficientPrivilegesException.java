package com.master.thesis.session.manager.service.exception;

import com.master.thesis.data.source.entity.Privilege;

/**
 * Created by miras108 on 2016-03-13.
 */
public class InsufficientPrivilegesException extends RuntimeException {

    private static final String EXCEPTION_MESSAGE = "Insufficient privileges to ";

    public InsufficientPrivilegesException(Privilege privilege)
    {
        super(EXCEPTION_MESSAGE + privilege.name().toLowerCase().replace("_", " "));
    }
}
