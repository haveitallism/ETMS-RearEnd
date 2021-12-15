package com.group8.service;

import com.group8.entity.EtmsCourse;

import java.util.List;

public interface EtmsCourseService {
    int findMyCourseSum(int uid);

    List<EtmsCourse> findAllCourse(int user_id);
}
