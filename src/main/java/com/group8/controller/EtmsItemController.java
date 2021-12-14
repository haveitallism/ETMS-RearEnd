package com.group8.controller;

import com.group8.entity.EtmsItem;
import com.group8.entity.EtmsOutline;
import com.group8.entity.ResponseEntity;
import com.group8.service.EtmsItemService;
import com.group8.service.EtmsItemStudentService;
import com.group8.service.EtmsOutlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/etmsItem")
public class EtmsItemController {
    @Autowired
    EtmsItemService etmsItemService;
    @Autowired
    EtmsItemStudentService etmsItemStudentService;
    @Autowired
    EtmsOutlineService etmsOutlineService;

    /*
    * 我的培训中参加的培训总数
    * */
    @RequestMapping("/findMyItemSum")
    public int findMyItemSum(int uid){
        return etmsItemStudentService.findMyItemSum(uid);
    }

    /*
    * 我的培训中参加的培训项目根据类别展示
    * */
    @RequestMapping("/findItemByType")
    public ResponseEntity<EtmsItem> findItemByType(int uid,String type){
        List<EtmsItem> list = etmsItemStudentService.findItemByType(uid,type);
        if(!list.isEmpty()){
            return new ResponseEntity(200,"查询成功",list);
        }else{
            return new ResponseEntity(400,"查询失败","");
        }
    }

    /*
    * 课程详情页中总的学习进度
    * */
    @RequestMapping("/findItemSchedule")
    public String findItemSchedele(int uid,int itemid){
        return etmsItemStudentService.findItemSchedele(uid,itemid);
    }

     /*课程详情页中所有的目录
     */
    @RequestMapping("/findAllDatalog")
    public ResponseEntity<List<String>> findAllCatalog(int itemid){
        List<String> list = etmsOutlineService.findAllCatalog(itemid);
        if(!list.isEmpty()){
            return new ResponseEntity(200,"查询成功",list);
        }else{
            return new ResponseEntity(400,"查询失败",null);
        }
    }


}
