package com.group8.service;

import com.group8.dto.RoleQueryCondition;
import com.group8.entity.EtmsRole;

import java.util.List;

public interface RoleService {
    List<EtmsRole> findAll(RoleQueryCondition roleQueryCondition);

    int deleteById(int roleId);

    int updateRole(EtmsRole etmsRole);

    int add(EtmsRole etmsRole);
}


