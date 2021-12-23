package com.group8.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.group8.dto.FormInLine;
import com.group8.dto.CourseFindByPage;
import com.group8.dto.EtmsCourseAbility;
import com.group8.entity.EtmsCourse;
import com.group8.entity.EtmsUser;
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
     * 我的必修中参加的培训总数
     * */
    @RequestMapping("/findMyRequiredSum/{uid}")
    public ResponseEntity<Integer> findMyCourseSum(@PathVariable int uid){
        int sum =  courseService.findMyCourseSum(uid);
        return new ResponseEntity<>(200,"查询成功",sum);
    }

    /*
     * 我的必修中参加的培训总数
     * */
    @RequestMapping("/findMyElectiveSum/{uid}")
    public ResponseEntity<Integer> findMyElectiveSum(@PathVariable int uid){
        int sum =  courseService.findMyElectiveSum(uid);
        return new ResponseEntity<>(200,"查询成功",sum);
    }

    /*
     * 我的必修中参加的课程展示
     * */
    @RequestMapping("/findAllRequired")
    public ResponseEntity<EtmsCourse> findAllRequired(@RequestBody CourseFindByPage courseFindByPage){
        PageHelper.startPage(courseFindByPage.getPage(),courseFindByPage.getLimit());
        List<EtmsCourse> list = courseService.findAllRequired(courseFindByPage.getId(),courseFindByPage.getEtmsCourse());
        PageInfo<EtmsCourse> etmsCoursePageInfo = new PageInfo<>(list);
        if(!list.isEmpty()){
            return new ResponseEntity(200,"查询成功",etmsCoursePageInfo);
        }else{
            return new ResponseEntity(400,"查询失败","");
        }
    }

    /*
     * 我的必修中参加的课程展示
     * */
    @RequestMapping("/findAllElective")
    public ResponseEntity<EtmsCourse> findAllElective(@RequestBody CourseFindByPage courseFindByPage){
        PageHelper.startPage(courseFindByPage.getPage(),courseFindByPage.getLimit());
        List<EtmsCourse> list = courseService.findAllElective(courseFindByPage.getId(),courseFindByPage.getEtmsCourse());
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

    /*
    * 课程详情页中通过courseId查找
    * */
    @RequestMapping("/findCourseById/{courseId}")
    public ResponseEntity<EtmsCourse> findCourseById(@PathVariable("courseId") int courseId){
        EtmsCourse course = courseService.findCourseById(courseId);
        return new ResponseEntity(200,"查询成功",course);
    }

    /*
    * 课程详情页中通过课程id查找学员
    * */
    @RequestMapping("/findStudentByCid")
    public ResponseEntity<PageInfo<EtmsUser>> findStudentByCid(@RequestBody FormInLine formInLine){
        int id = formInLine.getId();
        System.out.println(id);
        PageHelper.startPage(formInLine.getPage(),formInLine.getLimit());
        List<EtmsUser> list = courseService.findStudentByCid(formInLine.getId());
        PageInfo<EtmsUser> etmsUserPageInfo = new PageInfo(list);
            return new ResponseEntity(200, "查询成功", etmsUserPageInfo);

    }
}
