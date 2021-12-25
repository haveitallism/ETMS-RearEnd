package com.group8.service.impl;

import com.group8.dao.DeptDao;
import com.group8.entity.EtmsDept;
import com.group8.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DeptServiceImpl implements DeptService {
    @Autowired(required = false)
    DeptDao deptDao;

    @Override
    public List<EtmsDept> findAll() {
        return deptDao.findAll();
    }
}
