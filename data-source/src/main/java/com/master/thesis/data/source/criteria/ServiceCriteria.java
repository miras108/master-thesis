package com.master.thesis.data.source.criteria;

import com.master.thesis.data.source.entity.Service;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

public class ServiceCriteria {

    private SessionFactory sessionFactory;

    public ServiceCriteria(SessionFactory sessionFactory) {
        super();
        this.sessionFactory = sessionFactory;
    }

    public Service getByServiceId(Integer serviceId) {
        Session session = openSession();
        Criteria criteria = session.createCriteria(Service.class)
                .add(Restrictions.eq("serviceId", serviceId));

        return (Service) criteria.uniqueResult();
    }

    public Service getByServiceName(String serviceName) {
        Session session = openSession();
        Criteria criteria = session.createCriteria(Service.class)
                .add(Restrictions.eq("serviceName", serviceName));

        return (Service) criteria.uniqueResult();
    }

    private Session openSession() {
        return sessionFactory.openSession();
    }
}
