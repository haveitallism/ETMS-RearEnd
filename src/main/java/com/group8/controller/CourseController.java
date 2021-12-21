package com.group8.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.group8.dto.FormInLine;
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
@RequestMapping("/etmsCourse")
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
}
