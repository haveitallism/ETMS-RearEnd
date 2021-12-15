package com.group8.controller;

import com.group8.entity.EtmsItem;
import com.group8.entity.EtmsOutline;
import com.group8.entity.ResponseEntity;
import com.group8.service.EtmsItemService;
import com.group8.service.EtmsItemStudentService;
import com.group8.service.EtmsOutlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/etmsItem")
public class ItemController {

    @Autowired
    EtmsItemStudentService etmsItemStudentService;


    /*
    * 我的培训中参加的培训总数
    * */
    @RequestMapping("/findMyItemSum/{uid}")
    public int findMyItemSum(@PathVariable int uid){
        return etmsItemStudentService.findMyItemSum(uid);
    }

    /*
    * 我的培训中参加的培训项目根据类别展示
    * */
    @RequestMapping("/findItemByType/{user_id}")
    public ResponseEntity<EtmsItem> findItemByType(@PathVariable("user_id") int user_id){
        List<EtmsItem> list = etmsItemStudentService.findItemByType(user_id);
        if(!list.isEmpty()){
            return new ResponseEntity(200,"查询成功",list);
        }else{
            return new ResponseEntity(400,"查询失败","");
        }
    }

}
