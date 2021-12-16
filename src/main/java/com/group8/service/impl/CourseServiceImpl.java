package com.group8.service.impl;

import com.group8.dao.CourseDao;
import com.group8.entity.EtmsCourse;
import com.group8.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired(required = false)
    CourseDao courseDao;

    @Override
    public int findMyCourseSum(int uid) {
        return courseDao.findMyCourseSum(uid);
    }

    @Override
    public List<EtmsCourse> findAllCourse(int user_id) {
        return courseDao.findAllCourse(user_id);
    }
}
