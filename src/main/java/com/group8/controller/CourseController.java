package com.group8.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.group8.dto.CourseFindByPage;
import com.group8.dto.EtmsCourseAbility;
import com.group8.dto.FormInLine;
import com.group8.dto.UploadImg;
import com.group8.entity.EtmsCourse;
import com.group8.entity.ResponseEntity;
import com.group8.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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
     * 分页查询 包括关键字查询
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
     * 上传封面
     */
    @PostMapping("/uploadCover")
    public ResponseEntity<String> uploadPicture(UploadImg uploadImg) {
        System.out.println(uploadImg);
        String pictureUrl = courseService.uploadPicture(uploadImg);
        return new ResponseEntity(200,"上传成功！",pictureUrl);
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

    /**
     * 选课中心：热门课程展示 按照课程状态 流行 来排版
     */
    @RequestMapping("/hotCourses")
    public ResponseEntity<EtmsCourse> findHotCourse(){
        List<EtmsCourse> list = courseService.findHotCourses();
        return new ResponseEntity(200,"查询成功！",list);
    }

    /**
     * 选课中心：企业推荐课程 按照课程类型 必修 和创建时间最先 来排版
     */
    @RequestMapping("/companyRecommend")
    public ResponseEntity<EtmsCourse> companyRecommend(){
        List<EtmsCourse> list = courseService.findCompanyRecommend();
        return new ResponseEntity(200,"查询成功！",list);
    }
}
