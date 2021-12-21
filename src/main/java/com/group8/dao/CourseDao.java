package com.group8.dao;

import com.group8.entity.EtmsCourse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseDao {
    int findMyCourseSum(int uid);

    List<EtmsCourse> findAllCourse(int user_id);
    //查找所有课程 包括必修选修
    List<EtmsCourse> findAllCourse1(EtmsCourse etmsCourse);

    int addCourse(EtmsCourse etmsCourse);

    int deleteCourse(int courseId);
}
