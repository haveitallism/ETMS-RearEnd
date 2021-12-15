package com.group8.dao;

import com.group8.entity.EtmsUser;

import java.util.List;

public interface UserDao {
    List<EtmsUser> findByDeptId(int deptId);
}
