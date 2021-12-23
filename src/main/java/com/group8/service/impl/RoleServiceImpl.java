package com.group8.service.impl;

import com.group8.dao.PermDao;
import com.group8.dao.RoleDao;
import com.group8.dto.RoleQueryCondition;
import com.group8.entity.EtmsPerm;
import com.group8.entity.EtmsRole;
import com.group8.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired(required = false)
    RoleDao roleDao;
    @Autowired(required = false)
    PermDao permDao;

    @Override
    public List<EtmsRole> findAll(RoleQueryCondition roleQueryCondition) {
        return roleDao.findAll(roleQueryCondition);
    }

    @Override
    public int deleteById(int roleId) {
        //查询角色所有权限，有权限再去删除，无权限不删除
        List<EtmsPerm> permList = permDao.findByRoleId(roleId);
        if (permList.size() > 0) {
            //删除角色所有权限
            int j = roleDao.deleteRolePermById(roleId);
        }
        //删除角色表数据
        return roleDao.deleteById(roleId);
    }

    @Override
    public int updateRole(EtmsRole etmsRole) {
        //查询角色所有权限，有权限再去删除，无权限不删除
        List<EtmsPerm> permList = permDao.findByRoleId(etmsRole.getRoleId());
        if (permList.size() > 0) {
            //删除角色所有权限
            int j = roleDao.deleteRolePermById(etmsRole.getRoleId());
        }
        //重新添加角色权限
        if(etmsRole.getPermList().size() > 0) {
            int k = roleDao.addRolePerm(etmsRole);
        }
        //更新角色基本信息
        return roleDao.updateRole(etmsRole);
    }

    @Override
    public int add(EtmsRole etmsRole) {
        //新增角色基本信息 返回新增角色的id
        int i = roleDao.addRole(etmsRole);
        //添加角色权限
        if(etmsRole.getPermList().size() > 0) {
            int k = roleDao.addRolePerm(etmsRole);
        }
        return i;
    }
}
