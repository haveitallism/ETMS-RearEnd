package com.group8.controller;

import com.group8.entity.EtmsUser;
import com.group8.entity.ResponseEntity;
import com.group8.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired(required = false)
    UserService userService;

    /**
     * 根据部门id查询部门所有成员
     * @param deptId
     * @return
     */
    @RequestMapping("/findByDeptId/{deptId}")
    public ResponseEntity<List<EtmsUser>> findByDeptId(@PathVariable int deptId){
        List<EtmsUser> userList = userService.findByDeptId(deptId);
        return new ResponseEntity<>(200, userList);
    }
}
