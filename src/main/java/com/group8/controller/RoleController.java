package com.group8.controller;

import com.group8.dto.RoleQueryCondition;
import com.group8.entity.EtmsRole;
import com.group8.entity.ResponseEntity;
import com.group8.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired(required = false)
    RoleService roleService;

    /**
     * 新增角色及权限
     * @return
     */
    @RequestMapping("/addRole")
    public ResponseEntity<String> add(@RequestBody EtmsRole etmsRole){
        int i = roleService.add(etmsRole);
        if (i > 0) {
            return new ResponseEntity<>(200,"新增成功");
        }else {
            return new ResponseEntity<>(500,"新增失败");
        }
    }

    /**
     * 根据条件查询角色
     * @param roleQueryCondition
     * @return
     */
    @RequestMapping("/findAll")
    public ResponseEntity<List<EtmsRole>> findAll(@RequestBody RoleQueryCondition roleQueryCondition){
        List<EtmsRole> roleList = roleService.findAll(roleQueryCondition);
        if (roleList.size() > 0) {
            return new ResponseEntity<>(200,"查询成功",roleList);
        }else {
            return new ResponseEntity<>(500,"查询失败",null);
        }
    }

    /**
     * 根据id删除角色
     * @param roleId
     * @return
     */
    @RequestMapping("/deleteById/{roleId}")
    public ResponseEntity<String> deleteById(@PathVariable int roleId){
        int i = roleService.deleteById(roleId);
        if (i > 0) {
            return new ResponseEntity<>(200,"删除成功");
        }else {
            return new ResponseEntity<>(500,"删除失败");
        }
    }

    /**
     * 更新角色及权限
     * @param etmsRole
     * @return
     */
    @RequestMapping("/updateRole")
    public ResponseEntity<String> updateRole(@RequestBody EtmsRole etmsRole){
        int i = roleService.updateRole(etmsRole);
        if (i > 0) {
            return new ResponseEntity<>(200,"修改成功");
        }else {
            return new ResponseEntity<>(500,"修改失败");
        }
    }
}
