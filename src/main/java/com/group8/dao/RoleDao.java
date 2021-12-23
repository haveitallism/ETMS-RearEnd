package com.group8.dao;

import com.group8.dto.RoleQueryCondition;
import com.group8.entity.EtmsRole;

import java.util.List;

public interface RoleDao {
    List<EtmsRole> findAll(RoleQueryCondition roleQueryCondition);

    int deleteById(int roleId);

    int updateRole(EtmsRole etmsRole);

    int addRolePerm(EtmsRole etmsRole);

    int deleteRolePermById(long roleId);

    int addRole(EtmsRole etmsRole);
}
