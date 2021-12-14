package com.group8.controller;

import com.group8.entity.EtmsItem;
import com.group8.entity.ResponseEntity;
import com.group8.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping("/update")
    public ResponseEntity<String> update(@RequestBody EtmsItem etmsItem){
        int i = itemService.update(etmsItem);
        if (i > 0) {
            return new ResponseEntity<>(200, "修改成功");
        }else {
            return new ResponseEntity<>(500, "修改失败");
        }
    }

}
