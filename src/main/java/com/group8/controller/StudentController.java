package com.group8.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.group8.dto.FormInLine;
import com.group8.entity.EtmsItemStudent;
import com.group8.entity.ResponseEntity;
import com.group8.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired(required = false)
    StudentService studentService;

    /**
     * 根据项目id查询所有的报名的用户
     * @param formInLine 分页查询的dto，写前端时需要更改
     * @return 返回一个分好页的学员集合
     */
    @RequestMapping("/findAll")
    public ResponseEntity<PageInfo<EtmsItemStudent>> findAllByItemId(@RequestBody FormInLine formInLine){
        PageHelper.startPage(formInLine.getPage(), formInLine.getLimit());
        List<EtmsItemStudent> etmsItemStudentList = studentService.findAll(1);
        PageInfo<EtmsItemStudent> etmsItemStudentPageInfo = new PageInfo<>(etmsItemStudentList);
        return new ResponseEntity<>(200, etmsItemStudentPageInfo);
    }

}

