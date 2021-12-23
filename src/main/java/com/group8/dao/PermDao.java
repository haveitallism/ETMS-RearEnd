package com.group8.dao;

import com.group8.entity.EtmsPerm;

import java.util.List;

public interface PermDao {
    List<EtmsPerm> findAll();

    List<EtmsPerm> findByRoleId(long roleId);
}
