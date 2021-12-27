package com.group8.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.group8.dto.DemandPagedto;
import com.group8.entity.EtmsDemand;
import com.group8.entity.ResponseEntity;
import com.group8.service.DemandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/demand")
public class DemandController {
    @Autowired
    DemandService etmsDemandService;

    //分页查询加条件搜索
    @RequestMapping("/findAllDemandpage")
    public ResponseEntity<PageInfo<EtmsDemand>> findAllDemandpage(@RequestBody DemandPagedto demandPagedto) {
        PageHelper.startPage(demandPagedto.getPage(), demandPagedto.getLimit());
        List<EtmsDemand> etmsDemandList = etmsDemandService.findAllDemand(demandPagedto.getEtmsDemand());
        PageInfo<EtmsDemand> etmsDemandPageInfo = new PageInfo<>(etmsDemandList);
        if (etmsDemandList != null) {
            return new ResponseEntity(200, "查询成功", etmsDemandPageInfo);
        } else {
            return new ResponseEntity(500, "系统维护中");
        }

    }



    //查找个人发布的需求
    @RequestMapping("/findMyDemand/{uid}")
    public ResponseEntity findMyDemand(@PathVariable Integer uid) {
        List<EtmsDemand> etmsDemandList = etmsDemandService.findMyDemand(uid);
        System.out.println(etmsDemandList);
        return new ResponseEntity(200, "查询成功", etmsDemandList);
    }

    //查询具体发布需求
    @RequestMapping("/findDemandById/{demandTableId}")
    public ResponseEntity findDemandByid(@PathVariable int demandTableId) {
        EtmsDemand etmsDemand = etmsDemandService.findDemandByid(demandTableId);
        System.out.println(etmsDemand);
        return new ResponseEntity(200, "查询成功", etmsDemand);
    }

    //搜索发布需求
    @RequestMapping("/findDemandByName")
    public ResponseEntity findDemandByName(@RequestBody EtmsDemand etmsDemand) {

        List<EtmsDemand> etmsDemandlist = etmsDemandService.findDemandByName(etmsDemand.getDemandTitle(), etmsDemand.getUserId());
        return new ResponseEntity(200, "查询成功", etmsDemandlist);
    }

    //新增需求发布
    @RequestMapping("/addDemand")
    public ResponseEntity addDemand(@RequestBody EtmsDemand etmsDemand) {
        int i = etmsDemandService.addDemand(etmsDemand);
        if (i == 1) {
            return new ResponseEntity(200, "查询成功", i);
        } else {
            return new ResponseEntity(401, "查询失败", i);
        }
    }

    //根据id删除一个需求
    @DeleteMapping("/deleteDemandByid/{demandTableId}")
    public ResponseEntity<EtmsDemand> deleteDemandByid(@PathVariable int demandTableId) {
        int i = etmsDemandService.deleDemandById(demandTableId);
        if (i > 0) {
            return new ResponseEntity(200, "删除成功", i);
        } else {
            return new ResponseEntity(200, "删除成功", i);
        }
    }
}
