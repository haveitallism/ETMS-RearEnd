package com.group8.controller;

import com.group8.entity.EtmsUser;
import com.group8.entity.ResponseEntity;
import com.group8.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author acoffee
 * @create 2021-12-14 15:44
 */

@RequestMapping("/user")
@RestController

public class UserController {

    @Autowired
    UserService userService;


    @GetMapping("/findAllUser")
    public ResponseEntity<List<EtmsUser>> findAllUser(){
        List<EtmsUser> allUser = userService.findAllUser();
//        System.out.println(1111);
        ResponseEntity<List<EtmsUser>> entity;
//        System.out.println(allUser);
        if(allUser != null){
            entity = new ResponseEntity<>(20000,"查询成功！", allUser);
        }else{
            entity = new ResponseEntity<>(500,"查询失败！");
        }
        return entity;
    }

    @GetMapping("/findUserById/{id}")
    public ResponseEntity<EtmsUser> findUserById(@PathVariable int id){
        EtmsUser user = userService.findUserById(id);
        System.out.println(user);
        ResponseEntity<EtmsUser> entity;
        if(user != null){
            entity = new ResponseEntity<>(20000,"查询成功！", user);
        }else{
            entity = new ResponseEntity<>(500,"查询失败！");
        }
        return entity;
    }

    @PostMapping("/updateUser")
    public ResponseEntity<Boolean> updateUser(@RequestBody EtmsUser etmsUser){
        System.out.println(etmsUser);
        boolean flag = userService.updateUser(etmsUser);
        ResponseEntity<Boolean> entity;
        if(flag == true){
            entity = new ResponseEntity<>(20000,"修改成功！", flag);
        }else{
            entity = new ResponseEntity<>(500,"修改失败！");
        }
        return entity;
    }

    @PostMapping("/updatePassword/{id}/{newPassword}")
    public ResponseEntity<Boolean> updatePassword(@PathVariable int id,@PathVariable String newPassword){
        boolean flag = userService.updatePassword(id,newPassword);
        ResponseEntity<Boolean> entity;
        if(flag == true){
            entity = new ResponseEntity<>(200,"修改成功！", flag);
        }else{
            entity = new ResponseEntity<>(500,"修改失败！", !flag);
        }
        return entity;
    }

    @GetMapping("/validatePassword/{id}/{oldPassword}")
    public ResponseEntity<Boolean> validatePassword(@PathVariable("id") int id,@PathVariable("oldPassword") String oldPasswrod){

        boolean flag = userService.validatePassword(id,oldPasswrod);
        ResponseEntity<Boolean> entity;
        if(flag == true){
            entity = new ResponseEntity<>(20000,"密码正确！", flag);
        }else{
            entity = new ResponseEntity<>(500,"密码错误！", !flag);
        }
        return entity;
    }

    /**
     * 首页展示
     * @param id
     * @return
     */
    @GetMapping("/homePageDisplay/{id}")
    public ResponseEntity<EtmsUser> homePageDisplay(@PathVariable int id){
        EtmsUser etmsUser = userService.findUserIndexById(id);
        return new ResponseEntity<>(200,"展示成功",etmsUser);
    }

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
