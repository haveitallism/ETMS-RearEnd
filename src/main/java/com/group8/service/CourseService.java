package com.group8.service;

import com.group8.dto.EtmsCourseAbility;
import com.group8.dto.UploadImg;
import com.group8.entity.EtmsCourse;

import java.io.IOException;
import java.util.List;

public interface CourseService {
    int findMyCourseSum(int uid);

    List<EtmsCourse> findAllCourse(int user_id);

    List<EtmsCourse> findAllCourse1(EtmsCourse etmsCourse);

    int addCourse(EtmsCourseAbility etmsCourseAbility);

    int deleteCourse(int courseId);

    String uploadPicture(UploadImg uploadImg) ;

    List<EtmsCourse> findHotCourses();

    List<EtmsCourse> findCompanyRecommend();
}
