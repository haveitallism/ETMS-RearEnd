package com.group8.service.impl;

import com.group8.dao.EtmsItemStudentDao;
import com.group8.entity.EtmsItem;
import com.group8.service.EtmsItemStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EtmsItemStudentServiceImpl implements EtmsItemStudentService {
    @Autowired(required = false)
    EtmsItemStudentDao etmsItemStudentDao;
    @Override
    public int findMyItemSum(int uid) {
        return etmsItemStudentDao.findMyItemSum(uid);
    }

    @Override
    public List<EtmsItem> findItemByType(int uid) {
        return etmsItemStudentDao.findItemByType(uid);
    }

    @Override
    public String findItemSchedele(int uid, int itemid) {
        return etmsItemStudentDao.findItemSchedele(uid,itemid);
    }
}
