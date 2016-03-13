package com.master.thesis.session.manager.service;

import com.master.thesis.data.source.dao.ServiceDao;
import com.master.thesis.data.source.entity.Privilege;
import com.master.thesis.service.model.Service;

/**
 * Created by miras108 on 2016-02-28.
 */
public class SessionPrivilegeResolver
{
    private ServiceDao serviceDao;

    public boolean hasServicePrivilegeToCreateSession(Service requestedService)
    {
        com.master.thesis.data.source.entity.Service registeredService = getRegisteredServiceById(requestedService);
        return registeredService.getPrivilege() == Privilege.MANAGE_SESSION;
    }

    private com.master.thesis.data.source.entity.Service getRegisteredServiceById(Service requestedService)
    {
        return serviceDao.getServiceById(requestedService.getId());
    }

    public void setServiceDao(ServiceDao serviceDao)
    {
        this.serviceDao = serviceDao;
    }
}
