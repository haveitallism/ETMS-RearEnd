package com.group8.controller;

import com.group8.entity.EtmsDept;
import com.group8.entity.ResponseEntity;
import com.group8.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dept")
public class DeptController {

    @Autowired(required = false)
    DeptService deptService;

    /**
     * 查询所有部门
     * @return
     */
    @RequestMapping("/findAll")
    public ResponseEntity<List<EtmsDept>> findAll(){
        List<EtmsDept> deptList = deptService.findAll();
        return new ResponseEntity<>(200, deptList);
    }
}
