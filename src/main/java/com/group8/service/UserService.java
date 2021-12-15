package com.group8.service;

import com.group8.entity.EtmsUser;

import java.util.List;

public interface UserService {
    List<EtmsUser> findByDeptId(int deptId);
}
