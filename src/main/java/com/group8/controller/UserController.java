package com.group8.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.group8.dto.*;
import com.group8.entity.*;
import com.group8.service.StudentService;
import com.group8.service.UserService;
import com.group8.utils.QiniuUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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

    @Autowired
    private QiniuUtil qiniuUtil;

    @Autowired
    StudentService studentService;

    /**
     * 仅登录获取token
     * @param etmsUser
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody EtmsUser etmsUser){
        EtmsUser user = userService.login(etmsUser);
        if (user != null){
            return new ResponseEntity<>(200, "登录成功", user.getToken());
        }else {
            return new ResponseEntity<>(500, "登录失败", null);
        }
    }

    /**
     * 根据token获取用户角色信息
     * @param token
     * @return
     */
    @PostMapping("/getInfo/{token}")
    public ResponseEntity<EtmsUser> getInfo(@PathVariable String token){
        EtmsUser user = userService.getInfo(token);
        if (user != null){
            return new ResponseEntity<>(200, "获取成功", user);
        }else {
            return new ResponseEntity<>(500, "获取失败", null);
        }
    }

    @PostMapping("/logout/{token}")
    public ResponseEntity<String> logout(@PathVariable String token){
        boolean flag = userService.logout(token);
        if (flag){
            return new ResponseEntity<>(200, "登出成功");
        }else {
            return new ResponseEntity<>(500, "登出失败");
        }
    }

    @GetMapping("/findAllUser")
    public ResponseEntity<List<EtmsUser>> findAllUser(){
        List<EtmsUser> allUser = userService.findAllUser();
        ResponseEntity<List<EtmsUser>> entity;
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
        System.out.println(user);
        ResponseEntity<EtmsUser> entity;
        if(user != null){
            entity = new ResponseEntity<>(200,"查询成功！", user);
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
            entity = new ResponseEntity<>(200,"修改成功！", flag);
        }else{
            entity = new ResponseEntity<>(500,"修改失败！");
        }
        return entity;
    }

    @PostMapping("/updatePassword")
    public ResponseEntity<Boolean> updatePassword(@RequestBody UpdatePass updatePass){
        boolean flag = userService.updatePassword(updatePass.getId(),updatePass.getPass());
        ResponseEntity<Boolean> entity;
        if(flag == true){
            entity = new ResponseEntity<>(200,"修改成功！", flag);
        }else{
            entity = new ResponseEntity<>(500,"修改失败！", flag);
        }
        return entity;
    }

    @PostMapping("/validatePassword")
    public ResponseEntity<Boolean> validatePassword(@RequestBody UpdatePass updatePass){
        System.out.println(updatePass.getId());
        System.out.println(updatePass.getOldPassword());
        boolean flag = userService.validatePassword(updatePass.getId(),updatePass.getOldPassword());
        ResponseEntity<Boolean> entity;
        if(flag == true){
            entity = new ResponseEntity<>(200,"密码正确！", flag);
        }else if(flag == false){
            entity = new ResponseEntity<>(200,"密码错误！", flag);
        }else{
            entity = new ResponseEntity<>(500,"后台正在维护！", flag);
        }
        System.out.println(entity);

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

    /**
     * 上传头像
     * @param uploadImg
     * @return
     * @throws IOException
     */
    @PostMapping("/uploadPicture")
    public ResponseEntity<String> uploadPicture(UploadImg uploadImg) throws IOException {
        String pictureUrl = userService.uploadPicture(uploadImg);
        return new ResponseEntity(200,"上传成功！",pictureUrl);
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
            etmsUser.setUserPassword("123456");
            int i = userService.addStudent(etmsUser);
            if(i == 1){
                return new ResponseEntity(200,"添加成功!","成功保存一条数据");
            }else{
                return new ResponseEntity(400,"添加失败","用户名或角色为空");
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
        String userRole = etmsUser.getUserRole();
        System.out.println(userRole);

            int  b = userService.updateStudent(etmsUser);
            if(b == 1){
                return new ResponseEntity(200,"修改成功","成功修改一条数据");
            }else {
                return new ResponseEntity(400, "修改失败", "");
            }
    }

    @RequestMapping("/getStudentById/{userId}")
    public ResponseEntity<EtmsUser> getStudentById(@PathVariable int userId){

        EtmsUser etmsUser = userService.getStudentById(userId);
        return new ResponseEntity(200,"查询成功",etmsUser);
    }

    /*
    * 学员详情页中学员的能力模型查询
    * */
    @RequestMapping("/findAmById/{userId}")
    public ResponseEntity<List<EtmsAbilityModel>> findAmById(@PathVariable int userId){
        List<EtmsAbilityModel> abilityModelList = userService.findAmById(userId);
        return new ResponseEntity(200,"am查询成功",abilityModelList);
    }

    /**
     * 给用户添加课程
     */
    @PostMapping("/addCourse/{userId}/{courseId}")
    public ResponseEntity<String> addCourse(@PathVariable int userId,@PathVariable int courseId){
        int i = userService.addCourse(userId,courseId);
        if (i > 0){
            return new ResponseEntity<>(200, "添加课程成功");
        }else {
            return new ResponseEntity<>(501, "已收藏该课程");
        }
    }

    /**
     * 查找该用户所有的培训项目及课程信息
     */

    @PostMapping("/findCourseAndItem/{userId}")
    public ResponseEntity<CourseAndItem> findCourseAndItem(@PathVariable int userId){
        CourseAndItem courseAndItems = userService.findCourseAndItem(userId);
        System.out.println(courseAndItems);
        return new ResponseEntity<CourseAndItem>(200,"查询该用户课程和培训项目成功！",courseAndItems);
    }

    /**
     * 根据条件查询所有的项目
     * @param noJoinItemDto
     * @return
     */
    @RequestMapping("/findNoJoinItem")
    public ResponseEntity<PageInfo<EtmsItem>> findNoJoinItem(@RequestBody NoJoinItemDto noJoinItemDto){
        PageHelper.startPage(noJoinItemDto.getPage(),noJoinItemDto.getLimit());
        List<EtmsItem> itemList = userService.findNoJoinItem(noJoinItemDto);
        PageInfo<EtmsItem> itemPageInfo = new PageInfo<>(itemList);
        return new ResponseEntity<>(200,"查询成功！",itemPageInfo);
    }

    @RequestMapping("/applyItem")
    public ResponseEntity<String> applyItem(@RequestBody EtmsItemStudent etmsItemStudent){
        //新增前需要判断记录是否存在
        EtmsItemStudent student = studentService.findByItemIdAndUserId(etmsItemStudent.getItemId(), etmsItemStudent.getUserId());
        if(student == null){
            //学员不存在  可以报名
            int i = studentService.add(etmsItemStudent.getItemId(), etmsItemStudent.getUserId());
            if (i > 0) {
                return new ResponseEntity<>(200, "报名成功");
            }else {
                return new ResponseEntity<>(500, "报名失败");
            }
        }else{
            //学员已存在  不能报名
            return new ResponseEntity<>(200, "你已经报名了");
        }
    }
}
