package com.master.thesis.data.source.dao;

import com.master.thesis.data.source.criteria.ServiceCriteria;
import com.master.thesis.data.source.entity.Service;

public class ServiceDao {
    private ServiceCriteria serviceCriteria;

    public ServiceDao(ServiceCriteria serviceCriteria) {
        this.serviceCriteria = serviceCriteria;
    }

    public Service getServiceById(Integer serviceId) {
        return serviceCriteria.getByServiceId(serviceId);
    }

    public Service getServiceByName(String serviceName) {
        return serviceCriteria.getByServiceName(serviceName);
    }
}
