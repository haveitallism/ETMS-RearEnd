package com.group8.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.group8.dto.FormInLine;
import com.group8.dto.CourseFindByPage;
import com.group8.dto.EtmsCourseAbility;
import com.group8.entity.EtmsCourse;
import com.group8.entity.ResponseEntity;
import com.group8.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<Integer> findMyCourseSum(@PathVariable int uid){
        int sum =  courseService.findMyCourseSum(uid);
        return new ResponseEntity<>(200,"查询成功",sum);
    }

    /*
     * 选修课程中参加的课程展示
     * */
    @RequestMapping("/findAllCourse")
    public ResponseEntity<EtmsCourse> findAllCourse(@RequestBody FormInLine formInLine){
        PageHelper.startPage(formInLine.getPage(),formInLine.getLimit());
        List<EtmsCourse> list = courseService.findAllCourse(formInLine.getId());
        PageInfo<EtmsCourse> etmsCoursePageInfo = new PageInfo<>(list);
        if(!list.isEmpty()){
            return new ResponseEntity(200,"查询成功",etmsCoursePageInfo);
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

}
