package com.master.thesis.session.manager.service;

import com.master.thesis.data.source.entity.Privilege;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by miras108 on 2016-03-27.
 */
public class PrivilegesMapper {

    private final static Map<Privilege, com.master.thesis.service.model.Privilege> privilegeMap = new HashMap();

    static {
        privilegeMap.put(Privilege.LOYALTY, com.master.thesis.service.model.Privilege.LOYALTY);
        privilegeMap.put(Privilege.MANAGE_SESSION, com.master.thesis.service.model.Privilege.MANAGE_SESSION);
        privilegeMap.put(Privilege.OFFERS, com.master.thesis.service.model.Privilege.OFFERS);
        privilegeMap.put(Privilege.PRICE, com.master.thesis.service.model.Privilege.PRICE);
    }

    public com.master.thesis.service.model.Privilege map(Privilege privilege)
    {
        return privilegeMap.get(privilege);
    }

}
