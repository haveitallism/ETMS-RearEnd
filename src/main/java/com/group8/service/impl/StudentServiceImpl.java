package com.group8.service.impl;

import com.group8.dao.StudentDao;
import com.group8.dto.ScheduleQueryCondition;
import com.group8.entity.EtmsItemStudent;
import com.group8.entity.EtmsUser;
import com.group8.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired(required = false)
    StudentDao studentDao;

    @Override
    public List<EtmsItemStudent> findAll(int id) {
        return studentDao.findAll(id);
    }

    @Override
    public List<EtmsItemStudent> findApplied(ScheduleQueryCondition scheduleQueryCondition) {
        return studentDao.finApplied(scheduleQueryCondition);
    }

    @Override
    public int deleteByItemIdAndUserId(int itemId, int userId) {
        return studentDao.deleteByItemIdAndUserId(itemId, userId);
    }

    @Override
    public int add(int itemId, int userId) {
        return studentDao.add(itemId, userId);
    }



}
