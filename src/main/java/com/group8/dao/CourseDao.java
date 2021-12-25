package com.group8.dao;

import com.group8.dto.CourseFindByPage;
import com.group8.entity.EtmsCourse;
import com.group8.entity.EtmsUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseDao {
    int findMyRequiredSum(int uid);

    List<EtmsCourse> findAllRequired(@Param("uid") int uid,@Param("course") EtmsCourse etmsCourse);
    //查找所有课程 包括必修选修
    List<EtmsCourse> findAllCourse1(EtmsCourse etmsCourse);

    int addCourse(EtmsCourse etmsCourse);

    int findMyElectiveSum(int uid);

    List<EtmsCourse> findAllElective(@Param("uid") int uid,@Param("course") EtmsCourse etmsCourse);

    EtmsCourse findCourseById(int courseId);

    List<EtmsUser> findStudentByCid(int id);

    int deleteCourse(int courseId);

    List<EtmsCourse> findHotCourses();

    List<EtmsCourse> findCompanyRecommend();

    //打开指定id的课程
    EtmsCourse openCourse(int id);

    List<EtmsCourse> findAllCourse(int id);

    boolean DeleteCourseByUid(@Param("uid") int uid,@Param("cid") int cid);
}
