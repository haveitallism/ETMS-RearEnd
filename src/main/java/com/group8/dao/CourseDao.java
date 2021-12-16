package com.group8.dao;

import com.group8.entity.EtmsCourse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseDao {
    int findMyCourseSum(int uid);

    List<EtmsCourse> findAllCourse(int user_id);
}
