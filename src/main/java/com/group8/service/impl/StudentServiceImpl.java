package com.group8.service.impl;

import com.group8.dao.OutlineDao;
import com.group8.dao.StudentDao;
import com.group8.dto.ScheduleQueryCondition;
import com.group8.entity.EtmsItemStudent;
import com.group8.entity.EtmsOutline;
import com.group8.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    @Autowired(required = false)
    StudentDao studentDao;
    @Autowired(required = false)
    OutlineDao outlineDao;


    @Override
    public List<EtmsItemStudent> findAll(int id) {
        return studentDao.findAll(id);
    }

    @Override
    public List<EtmsItemStudent> findApplied(ScheduleQueryCondition scheduleQueryCondition) {
        return studentDao.finApplied(scheduleQueryCondition);
    }

    @Override
    public int deleteById(int stuId) {
        return studentDao.deleteById(stuId);
    }

    @Override
    public int add(long itemId, long userId) {
        List<EtmsOutline> outlineList = outlineDao.findAll(itemId);
        studentDao.addStuSchedele(userId, itemId, outlineList);
        return studentDao.add(itemId, userId);
    }

    @Override
    public int updateApplyStatus(String status, int stuId) {
        return studentDao.updateApplyStatus(status, stuId);
    }

    @Override
    public EtmsItemStudent findByItemIdAndUserId(long itemId, long userId) {
        return studentDao.findByItemIdAndUserId(itemId, userId);
    }

}
