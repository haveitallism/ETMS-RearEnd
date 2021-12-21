package com.group8.controller;

import com.group8.dto.FormInLine;
import com.group8.entity.EtmsCatalog;
import com.group8.entity.ResponseEntity;
import com.group8.service.OutlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/outline")
public class OutlineController {
    @Autowired(required = false)
    OutlineService outlineService;

    /**
     * 查询该项目所有的目录及目录下的课件
     * @param formInLine 传入的项目id
     * @return 返回一个目录集合
     */
    @PostMapping(value = "/findByItemId")
    public ResponseEntity<List<EtmsCatalog>> findByItemId(@RequestBody FormInLine formInLine){
        System.out.println(formInLine.toString());
        //PageHelper.startPage(formInLine.getPage(), formInLine.getLimit());
        List<EtmsCatalog> catalogList = outlineService.findByItemId(formInLine.getId());
//        PageInfo<EtmsCatalog> etmsItemStudentPageInfo = new PageInfo<>(catalogList);
        return new ResponseEntity<>(200, catalogList);
    }

    /**
     *根据大纲id上传对应的课件
     * @param id 大纲id
     * @return 返回1成功，0失败
     */
    @RequestMapping("/uploadClassFile/{id}")
    public ResponseEntity<String> uploadClassFile(@PathVariable int id){
        int i = outlineService.uploadClassFile(id, "");
        if (i > 0) {
            return new ResponseEntity<>(200, "上传成功");
        }else {
            return new ResponseEntity<>(500, "上传失败");
        }
    }

    /**
     *根据大纲id删除对应的课件
     * @param id 大纲id
     * @return 返回1成功，0失败
     */
    @RequestMapping("/deleteClassFile/{id}")
    public ResponseEntity<String> deleteClassFile(@PathVariable int id){
        int i = outlineService.deleteClassFile(id);
        if (i > 0) {
            return new ResponseEntity<>(200, "删除成功");
        }else {
            return new ResponseEntity<>(500, "删除失败");
        }
    }
}

