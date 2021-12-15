package com.group8.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.group8.dto.FormInLine;
import com.group8.dto.ScheduleQueryCondition;
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
     *
     * @param formInLine 分页查询的dto，写前端时需要更改
     * @return 返回一个分好页的学员集合
     */
    @RequestMapping("/findAll")
    public ResponseEntity<PageInfo<EtmsItemStudent>> findAllByItemId(@RequestBody FormInLine formInLine) {
        PageHelper.startPage(formInLine.getPage(), formInLine.getLimit());
        List<EtmsItemStudent> etmsItemStudentList = studentService.findAll(formInLine.getId());
        PageInfo<EtmsItemStudent> etmsItemStudentPageInfo = new PageInfo<>(etmsItemStudentList);
        return new ResponseEntity<>(200, etmsItemStudentPageInfo);
    }

    /**
     * 根据项目id查询所有报名成功的用户
     *
     * @param scheduleQueryCondition 查询条件的dto，写前端时需要更改
     * @return 返回一个分好页的学员集合
     */
    @RequestMapping("/findApplied")
    public ResponseEntity<PageInfo<EtmsItemStudent>> findAppliedByItemId(@RequestBody ScheduleQueryCondition scheduleQueryCondition) {
        PageHelper.startPage(scheduleQueryCondition.getPage(), scheduleQueryCondition.getLimit());
        List<EtmsItemStudent> etmsItemStudentList = studentService.findApplied(scheduleQueryCondition);
        PageInfo<EtmsItemStudent> etmsItemStudentPageInfo = new PageInfo<>(etmsItemStudentList);
        return new ResponseEntity<>(200, etmsItemStudentPageInfo);
    }

    /**
     * 根据用户id和项目id删除学员表记录
     * @param itemId 当前项目id
     * @param userId 选择用户id
     * @return
     */
    @RequestMapping("/delete/{itemId}/{userId}")
    public ResponseEntity<String> deleteByItemIdAndUserId(@PathVariable int itemId, @PathVariable int userId) {
        int i = studentService.deleteByItemIdAndUserId(itemId, userId);
        if (i > 0) {
            return new ResponseEntity<>(200, "删除成功");
        }else {
            return new ResponseEntity<>(500, "删除失败");
        }
    }

    @RequestMapping("/add/{itemId}/{userId}")
    public ResponseEntity<String> add(@PathVariable int itemId, @PathVariable int userId){
        //新增前需要判断记录是否存在
        int i = studentService.add(itemId, userId);
        if (i > 0) {
            return new ResponseEntity<>(200, "添加成功");
        }else {
            return new ResponseEntity<>(500, "添加失败");
        }
    }
}

