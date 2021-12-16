package com.group8.controller;

import com.group8.dto.ItemidAndCatalogName;
import com.group8.dto.UseridAndItemid;
import com.group8.entity.EtmsCatalog;
import com.group8.entity.EtmsClassFile;
import com.group8.entity.EtmsItem;
import com.group8.entity.ResponseEntity;
import com.group8.service.ItemService;
import io.lettuce.core.ScriptOutputType;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 培训详情页1：通过培训id查询目录
     * @param id
     * @return
     */
    @GetMapping("/findCatalogByTid/{id}")
    public ResponseEntity<List<EtmsCatalog>> findCatalogByTid(@PathVariable int id){
        List<EtmsCatalog> catalogs = itemService.findCatalogByTid(id);
        ResponseEntity<List<EtmsCatalog>> entity;
        if(catalogs != null){
            entity = new ResponseEntity<>(200,"查询成功！",catalogs);
        }else {
            entity = new ResponseEntity<>(500,"查询失败！",catalogs);
        }
        return entity;
    }

    /**
     * 通过培训id和目录名去查询课程内容
     * @return
     */
    @RequestMapping("/findClassFileByCid")
    public ResponseEntity<List<EtmsClassFile>> findClassFileByCid(@RequestBody ItemidAndCatalogName dto){
        List<EtmsClassFile> classFiles = itemService.findClassFileByCid(dto.getId(),dto.getCatalogName());
        ResponseEntity<List<EtmsClassFile>> entity;
        if(classFiles != null){
            entity = new ResponseEntity<>(200,"查询成功！",classFiles);
        }else {
            entity = new ResponseEntity<>(500,"查询失败！",classFiles);
        }
        return entity;
    }

    /**
     * 通过培训课程的Id和学员Id去查培训课程的进度
     * @return
     */
    @GetMapping("/findTrainSchByTid")
    public ResponseEntity<String> findTrainSchByTid(@RequestBody UseridAndItemid dto){
        String schedule = itemService.findSchedule(dto.getUid(), dto.getTid());
        ResponseEntity<String> entity;
        if(schedule != null){
            entity = new ResponseEntity<>(200,"查询成功！",schedule);
        }else {
            entity = new ResponseEntity<>(500,"查询失败！",schedule);
        }
        return entity;
    }


    /**
     * 通过培训id查看此次培训课程数量
     * @param tid
     * @return
     */
    @GetMapping("/findAllClass/{tid}")
    public ResponseEntity<String> findAllClass(@PathVariable int tid){
        String classNum = itemService.findClassNum(tid);
        ResponseEntity<String> entity;
        if(classNum != null){
            entity = new ResponseEntity<>(200,"查询成功！",classNum);
        }else {
            entity = new ResponseEntity<>(500,"查询失败！",classNum);
        }
        return entity;
    }

}
