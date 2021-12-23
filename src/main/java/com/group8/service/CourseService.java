package com.group8.service;

import com.group8.dto.CourseFindByPage;
import com.group8.dto.EtmsCourseAbility;
import com.group8.dto.UploadFile;
import com.group8.dto.UploadImg;
import com.group8.entity.EtmsCourse;
import com.group8.entity.EtmsUser;

import java.io.IOException;
import java.util.List;

public interface CourseService {
    int findMyCourseSum(int uid);

    List<EtmsCourse> findAllRequired(int uid,EtmsCourse etmsCourse);

    List<EtmsCourse> findAllCourse1(EtmsCourse etmsCourse);

    int addCourse(EtmsCourseAbility etmsCourseAbility);

    int findMyElectiveSum(int uid);

    List<EtmsCourse> findAllElective(int id, EtmsCourse etmsCourse);

    EtmsCourse findCourseById(int courseId);

    List<EtmsUser> findStudentByCid(int id);

    int deleteCourse(int courseId);

    String uploadPicture(UploadImg uploadImg) ;

    List<EtmsCourse> findHotCourses();

    List<EtmsCourse> findCompanyRecommend();

    String uploadFile(UploadFile uploadFile);
}
