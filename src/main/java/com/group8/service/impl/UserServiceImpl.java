package com.group8.service.impl;

import com.group8.dao.UserDao;
import com.group8.entity.EtmsUser;
import com.group8.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired(required = false)
    UserDao userDao;

    @Override
    public List<EtmsUser> findByDeptId(int deptId) {
        return userDao.findByDeptId(deptId);
    }
}
