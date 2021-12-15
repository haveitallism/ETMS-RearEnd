package com.group8.service.impl;

import com.group8.dao.EtmsCourseDao;
import com.group8.entity.EtmsCourse;
import com.group8.service.EtmsCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EtmsCourseServiceImpl implements EtmsCourseService {
    @Autowired(required = false)
    EtmsCourseDao etmsCourseDao;

    @Override
    public int findMyCourseSum(int uid) {
        return etmsCourseDao.findMyCourseSum(uid);
    }

    @Override
    public List<EtmsCourse> findAllCourse(int user_id) {
        return etmsCourseDao.findAllCourse(user_id);
    }
}
