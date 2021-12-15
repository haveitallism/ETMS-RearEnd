package com.group8.controller;

import com.group8.entity.EtmsCourse;
import com.group8.entity.EtmsItem;
import com.group8.entity.ResponseEntity;
import com.group8.service.EtmsCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/etmsCourse")
public class CourseController {
    @Autowired
    EtmsCourseService etmsCourseService;

    /*
     * 选修课程中参加的培训总数
     * */
    @RequestMapping("/findMyCourseSum/{uid}")
    public int findMyCourseSum(@PathVariable int uid){
        return etmsCourseService.findMyCourseSum(uid);
    }

    /*
     * 选修课程中参加的课程展示
     * */
    @RequestMapping("/findAllCourse/{user_id}")
    public ResponseEntity<EtmsCourse> findAllCourse(@PathVariable("user_id") int user_id){
        List<EtmsCourse> list = etmsCourseService.findAllCourse(user_id);
        if(!list.isEmpty()){
            return new ResponseEntity(200,"查询成功",list);
        }else{
            return new ResponseEntity(400,"查询失败","");
        }
    }
}
