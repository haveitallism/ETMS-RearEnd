package com.group8.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.group8.dto.EtmsItemAbilityOutline;
import com.group8.dto.ItemFindByPage;
import com.group8.entity.EtmsItem;
import com.group8.entity.ResponseEntity;
import com.group8.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired(required = false)
    ItemService itemService;

    /**
     * 根据id查询培训项目
     * @param id
     * @return 返回一个自定义实体类
     */
    @RequestMapping("/findById/{id}")
    public ResponseEntity<EtmsItem> findById(@PathVariable int id){
        EtmsItem item = itemService.findById(id);
        return new ResponseEntity<>(200, item);
    }

    /**
     * 根据id修改培训项目
     * @param etmsItem 封装的项目对象
     * @return 返回1成功，0失败
     */
    @PostMapping("/update")
    public ResponseEntity<String> update(@RequestBody EtmsItem etmsItem){
        int i = itemService.update(etmsItem);
        if (i > 0) {
            return new ResponseEntity<>(200, "修改成功");
        }else {
            return new ResponseEntity<>(500, "修改失败");
        }
    }

    //添加培训项目 返回一个包含item outline ability 的DTO
    @RequestMapping("/addItem")
    public ResponseEntity addItem(@RequestBody EtmsItemAbilityOutline etmsItemAbilityOutline){
        int i = itemService.addItem(etmsItemAbilityOutline);
        if (i > 0 ){
                return  new ResponseEntity(200,"添加成功");
            }else{
                return  new ResponseEntity(200,"添加失败");
        }
    }

    //根据不同关键字分页查询培训项目
    @RequestMapping("/findItem")
    public  ResponseEntity<PageInfo<EtmsItem>> findItem(@RequestBody ItemFindByPage itemFindByPage){
        PageHelper.startPage(itemFindByPage.getPage(),itemFindByPage.getLimit());
        List<EtmsItem> list = itemService.findItem(itemFindByPage.getEtmsItem());
        PageInfo<EtmsItem> etmsItemPageInfo = new PageInfo<>(list);
        return new ResponseEntity<PageInfo<EtmsItem>>(200,"查询成功",etmsItemPageInfo);
    }


    /*
     * 我的培训中参加的培训总数
     * */
    @RequestMapping("/findMyItemSum/{uid}")
    public int findMyItemSum(@PathVariable int uid){
        return itemService.findMyItemSum(uid);
    }

    /*
     * 我的培训中参加的培训项目根据类别展示
     * */
    @RequestMapping("/findAllItem/{user_id}")
    public ResponseEntity<EtmsItem> findAllItem(@PathVariable("user_id") int user_id){
        List<EtmsItem> list = itemService.findAllItem(user_id);
        if(!list.isEmpty()){
            return new ResponseEntity(200,"查询成功",list);
        }else{
            return new ResponseEntity(400,"查询失败","");
        }
    }


}
