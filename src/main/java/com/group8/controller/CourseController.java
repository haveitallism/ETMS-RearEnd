package com.group8.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.group8.dto.CourseFindByPage;
import com.group8.dto.EtmsCourseAbility;
import com.group8.entity.EtmsCourse;
import com.group8.entity.ResponseEntity;
import com.group8.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    CourseService courseService;

    /*
     * 选修课程中参加的培训总数
     * */
    @RequestMapping("/findMyCourseSum/{uid}")
    public int findMyCourseSum(@PathVariable int uid){
        return courseService.findMyCourseSum(uid);
    }

    /*
     * 选修课程中参加的课程展示
     * */
    @RequestMapping("/findAllCourse/{user_id}")
    public ResponseEntity<EtmsCourse> findAllCourse(@PathVariable("user_id") int user_id){
        List<EtmsCourse> list = courseService.findAllCourse(user_id);
        if(!list.isEmpty()){
            return new ResponseEntity(200,"查询成功",list);
        }else{
            return new ResponseEntity(400,"查询失败","");
        }
    }

    /**
     * 查询所有的课程 包括必修 选修
     * 分压查询 包括关键字查询
     */
    @RequestMapping("/allCourses")
    public ResponseEntity<PageInfo<EtmsCourse>> findAllCourse1(@RequestBody CourseFindByPage courseFindByPage){
        System.out.println("获取的DTO"+courseFindByPage);
        PageHelper.startPage(courseFindByPage.getPage(),courseFindByPage.getLimit());
        List<EtmsCourse> list = courseService.findAllCourse1(courseFindByPage.getEtmsCourse());
        PageInfo<EtmsCourse> etmsCoursePageInfo = new PageInfo<>(list);
        return new ResponseEntity<PageInfo<EtmsCourse>>(200,"查询成功",etmsCoursePageInfo);
    }

    /**
     *
     * 新增课程
     */
    @RequestMapping("/addCourse")
    public ResponseEntity<EtmsCourse> addCourse(@RequestBody EtmsCourseAbility etmsCourse){
        int i = courseService.addCourse(etmsCourse);
        if (i > 0){
            return new ResponseEntity<>(200, "添加成功");
        }else {
            return new ResponseEntity<>(500, "添加失败");
        }
    }

    /**
     * 删除课程
     */
    @DeleteMapping("deleteCourse/{courseId}")
    public ResponseEntity<EtmsCourse> deleteCourse(@PathVariable int courseId){
        int i = courseService.deleteCourse(courseId);
        if (i > 0){
            return new ResponseEntity<>(200, "删除成功");
        }else {
            return new ResponseEntity<>(500, "删除失败");
        }
    }
}
