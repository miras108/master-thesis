package com.master.thesis.session.manager.service;

import com.master.thesis.data.source.dao.ServiceDao;
import com.master.thesis.data.source.entity.Privilege;
import com.master.thesis.service.model.Service;
import com.master.thesis.service.model.Session;

/**
 * Created by miras108 on 2016-02-28.
 */
public class SessionPrivilegeResolver {
    private ServiceDao serviceDao;
    private PrivilegesMapper privilegesMapper;

    public boolean hasServicePrivilegeToManageSession(Service requestedService) {
        return hasServicePrivilegeTo(requestedService, Privilege.MANAGE_SESSION);
    }

    private boolean hasServicePrivilegeTo(Service requestedService, Privilege privilege) {
        com.master.thesis.data.source.entity.Service registeredService = getRegisteredServiceById(requestedService.getId());
        return registeredService != null ? registeredService.getPrivilege() == privilege : false;
    }

    private com.master.thesis.data.source.entity.Service getRegisteredServiceById(int requestedServiceId) {
        return serviceDao.getServiceById(requestedServiceId);
    }

    public boolean hasServicePrivilegeToSession(Service requestedService, Session session) {
        com.master.thesis.data.source.entity.Service service = getRegisteredServiceById(requestedService.getId());
        if (service != null) {
            final com.master.thesis.service.model.Privilege servicePrivilege = privilegesMapper.map(service.getPrivilege());
            return sessionPrivilegeContainsPrivilege(session, servicePrivilege);
        }
        return false;
    }

    private boolean sessionPrivilegeContainsPrivilege(Session session, com.master.thesis.service.model.Privilege servicePrivilege) {
        return session.getPrivileges().getPrivilege().stream()
                .filter(privilege -> privilege == servicePrivilege)
                .findFirst()
                .isPresent();
    }

    public void setServiceDao(ServiceDao serviceDao) {
        this.serviceDao = serviceDao;
    }

    public void setPrivilegesMapper(PrivilegesMapper privilegesMapper) {
        this.privilegesMapper = privilegesMapper;
    }
}
