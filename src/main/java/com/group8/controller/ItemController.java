package com.group8.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.group8.dto.*;
import com.group8.entity.*;
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

    @RequestMapping("/findAMById")
    public ResponseEntity<List<EtmsAbilityModel>> findAMById(@RequestBody FormInLine formInLine){
        List<EtmsAbilityModel> abilityModelList = itemService.findAMById(formInLine.getId());
        return new ResponseEntity<>(200, abilityModelList);
    }

    /**
     * 根据id查询培训项目
     * @param id
     * @return 返回一个自定义实体类
     */
    @RequestMapping("/findById/{id}")
    public ResponseEntity<EtmsItem> findById(@PathVariable int id){
        EtmsItem item = itemService.findById(id);
        if(item != null){
            return new ResponseEntity<>(200, "查询成功", item);
        }else{
            return new ResponseEntity<>(500, "查询失败", null);
        }
    }

    /**
     * 根据id修改培训项目
     * @param etmsItem 封装的项目对象
     * @return 返回1成功，0失败
     */
    @PostMapping("/update")
    public ResponseEntity<String> update(@RequestBody EtmsItem etmsItem){
        System.out.println(etmsItem);
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

    //添加培训项目 返回一个包含item outline ability 的DTO
    @RequestMapping("/addItem")
    public ResponseEntity<String> addItem(@RequestBody EtmsItemAbilityOutline etmsItemAbilityOutline){
        System.out.println(etmsItemAbilityOutline);
        int i = itemService.addItem(etmsItemAbilityOutline);
        if (i > 0 ){
                return  new ResponseEntity<>(200,"添加成功");
            }else{
                return  new ResponseEntity<>(200,"添加失败");
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

    //根据项目id删除相应的培训项目
    @DeleteMapping("/deleteOne/{itemId}")
    public ResponseEntity<PageInfo<EtmsItem>> deleteItem(@PathVariable int itemId){
        int i  = itemService.deleteOne(itemId);
        if (i > 0 ){
            return  new ResponseEntity(200,"删除成功");
        }else{
            return  new ResponseEntity(200,"删除失败");
        }
    }

    /*
     * 我的培训中参加的培训总数
     * */
    @RequestMapping("/findMyItemSum/{uid}")
    public ResponseEntity<Integer> findMyItemSum(@PathVariable int uid){
        int sum = itemService.findMyItemSum(uid);
        return new ResponseEntity<>(200,"查询成功",sum);
    }

    /*
     * 我的培训中参加的培训项目根据类别展示
     * */
    @PostMapping("/findAllItem")
    public ResponseEntity<List<EtmsItem>> findAllItem(@RequestBody FormInLine formInLine){
        System.out.println(formInLine);
        PageHelper.startPage(formInLine.getPage(),formInLine.getLimit());
        int id = formInLine.getId();
        List<EtmsItem> list = itemService.findAllItem(id);
        System.out.println(list);
        PageInfo<EtmsItem> etmsItemPageInfo = new PageInfo<>(list);
        if(!list.isEmpty()){
            return new ResponseEntity(200,"查询成功",etmsItemPageInfo);
        }else{
            return new ResponseEntity(400,"查询失败","");
        }
    }

    @RequestMapping("/updateAbilityModel")
    public ResponseEntity<EtmsItem> updateAbilityModel(@RequestBody AbilityModelSubject abilityModelSubject) {
        abilityModelSubject.setSubject("item");
        int i = itemService.updateAbilityModel(abilityModelSubject);
        if (i > 0){
            return new ResponseEntity<>(200, "修改成功");
        }else {
            return new ResponseEntity<>(500, "修改失败");
        }
    }

    /**
     * 查询outline中所有信息
     * @return
     */
    @RequestMapping("/findCatalogInfo")
    public ResponseEntity<List<EtmsOutline>> findOutlineInfo(@RequestBody UserAndItemid userAndItemid){
        List<EtmsOutline> itemInfo = itemService.findItemInfo(userAndItemid.getUserId(),userAndItemid.getItemId(),userAndItemid.getCatalog());
        if(itemInfo != null){
            return new ResponseEntity(200,"查询成功",itemInfo);
        }else{
            return new ResponseEntity(400,"查询失败","");
        }
    }

    /**
     * 查询对应的视频
     * @param etmsOutline
     * @return
     */
    @RequestMapping("/openClassFile")
    public ResponseEntity<String> findClassVideo(@RequestBody EtmsOutline etmsOutline){
        String classVideoName = itemService.findClassVideo(etmsOutline.getItemId(), etmsOutline.getCatalog(), etmsOutline.getTrainClassTitle());
        System.out.println(classVideoName);
        if(classVideoName != null){
            return new ResponseEntity(200,"查询成功",classVideoName);
        }else{
            return new ResponseEntity(400,"查询失败","");
        }
    }

    /**
     * 查询详情页一中的进度以及时间
     * @return
     */
    @PostMapping("/findScheduleAndHour")
    public ResponseEntity<TrainAndCatalogSchedule> findScheduleAndHour(@RequestBody UserAndItemid userAndItemid){

        System.out.println(userAndItemid);
        TrainAndCatalogSchedule scheduleAndHour = itemService.findScheduleAndHour(userAndItemid.getUserId(), userAndItemid.getItemId());

        if(scheduleAndHour != null){
            return new ResponseEntity(200,"查询成功",scheduleAndHour);
        }else{
            return new ResponseEntity(400,"查询失败","");
        }
    }

    /**
     * 记录视频播放时长
     * @param userAndItemid
     * @return
     */
    @RequestMapping("/recordVideoProgress")
    public ResponseEntity<Boolean> recordVideoProgress(@RequestBody UserAndItemid userAndItemid){
        boolean flag = itemService.recordVideoProgress(userAndItemid);
        if(flag){
            return new ResponseEntity(200,"记录成功",flag);
        }else{
            return new ResponseEntity(500,"记录失败",flag);
        }
    }

    /**
     * 包括视频看完就去更新总培训进度,并且为每个人增加相应的能力
     * @param userAndItemid
     * @return
     */
    @RequestMapping("/updateItemSchedule")
    public ResponseEntity<Boolean> updateItemSchedule(@RequestBody UserAndItemid userAndItemid){

        boolean flag = itemService.updateItemSchedule(userAndItemid);
        if(flag){
            return new ResponseEntity(200,"更新成功",flag);
        }else{
            return new ResponseEntity(500,"更新失败",flag);
        }
    }
}
