package com.group8.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.group8.dto.EtmsItemAbilityOutline;
import com.group8.dto.ItemFindByPage;
import com.group8.entity.EtmsItem;
import com.group8.entity.ResponseEntity;
import com.group8.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author QY
 * @create 2021-12-14 15:54
 */

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired(required = false)
    ItemService itemService ;

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


}
