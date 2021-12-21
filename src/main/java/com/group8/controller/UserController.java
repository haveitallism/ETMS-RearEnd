package com.group8.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.group8.dto.FormInLine;
import com.group8.dto.StudentFindByPage;
import com.group8.entity.EtmsUser;
import com.group8.entity.ResponseEntity;
import com.group8.service.UserService;
import org.apache.coyote.Response;
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


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody EtmsUser etmsUser){
        EtmsUser user = userService.login(etmsUser);
        if (user != null){
            return new ResponseEntity<>(200, "登录成功");
        }else {
            return new ResponseEntity<>(500, "登录失败");
        }
    }

    @GetMapping("/findAllUser")
    public ResponseEntity<List<EtmsUser>> findAllUser(){
        List<EtmsUser> allUser = userService.findAllUser();
        ResponseEntity<List<EtmsUser>> entity;
        System.out.println(allUser);
        if(allUser != null){
            entity = new ResponseEntity<>(200,"查询成功！", allUser);
        }else{
            entity = new ResponseEntity<>(500,"查询失败！");
        }
        return entity;
    }

    @GetMapping("/findUserById/{id}")
    public ResponseEntity<EtmsUser> findUserById(@PathVariable int id){
        EtmsUser user = userService.findUserById(id);
        ResponseEntity<EtmsUser> entity;
        if(user != null){
            entity = new ResponseEntity<>(200,"查询成功！", user);
        }else{
            entity = new ResponseEntity<>(500,"查询失败！");
        }
        return entity;
    }

    @PostMapping("/updateUser")
    public ResponseEntity<Boolean> updateUser(EtmsUser etmsUser){
        boolean flag = userService.updateUser(etmsUser);
        ResponseEntity<Boolean> entity;
        if(flag == true){
            entity = new ResponseEntity<>(200,"修改成功！", flag);
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
            entity = new ResponseEntity<>(200,"密码正确！", flag);
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

    /*
    * 学员管理中通过条件查询所有学员
    * */
    @RequestMapping("/findStudentBySearch")
    public ResponseEntity<List<EtmsUser>> findAllStudent(@RequestBody StudentFindByPage studentFindByPage){
        PageHelper.startPage(studentFindByPage.getPage(),studentFindByPage.getLimit());
        List<EtmsUser> list = userService.findAllStudent(studentFindByPage.getEtmsUser());
        PageInfo<EtmsUser> etmsUserPageInfo = new PageInfo<>(list);
        if(!list.isEmpty()){
            return new ResponseEntity(200,"查询成功",etmsUserPageInfo);
        }else{
            return new ResponseEntity(400,"查询失败","");
        }
    }

    /*
    * 学员管理中添加学员  即添加用户
    * 需要判断是否存在
    * 若存在，则添加失败
    * 若不存在就直接添加
    * */
    @RequestMapping("/addStudent")
    public ResponseEntity<String> addStudent(@RequestBody EtmsUser etmsUser){
        String userName = etmsUser.getUserName();
        String userRole = etmsUser.getUserRole();
        if(! (userName.equals("") && userRole.equals(""))){
            int i = userService.addStudent(etmsUser);
            if(i == 1){
                return new ResponseEntity(200,"添加成功!","成功保存一条数据");
            }else{
                return new ResponseEntity(400,"添加失败","");
            }
        }else{
            return new ResponseEntity(400,"添加失败","不能为空");
        }
    }

    /*
    * 学员管理中删除某条学员数据
    * */
    @RequestMapping("/deleteStudent/{userId}")
    public ResponseEntity<String> deleteStudent(@PathVariable int userId){
        int i = userService.deleteStudent(userId);
        if(i == 0){
            return new ResponseEntity(200,"删除成功","成功删除一条数据");
        }else{
            return new ResponseEntity(400,"删除失败","");
        }
    }

    /*
    * 学员管理中修改某条学员的数据
    * */
    @RequestMapping("/updateStudent")
    public ResponseEntity<String> updateStudent(@RequestBody EtmsUser etmsUser){
        boolean b = userService.updateStudent(etmsUser);

    }
}
