package com.group8.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.group8.dto.DemandPagedto;
import com.group8.entity.EtmsDemand;
import com.group8.entity.ResponseEntity;
import com.group8.service.DemandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


//    @RequestMapping("/findAllDemand")
//    public ResponseEntity<EtmsDemand> findAllDemand(){
//        List<EtmsDemand> etmsDemandList = etmsDemandService.findAllDemand();
//        if(etmsDemandList!=null){
//            return  new ResponseEntity(200,"查询成功",etmsDemandList);
//        }else {
//            return  new ResponseEntity(500,"系统维护中");
//        }
//
//    }


    //查找个人发布的需求
    @RequestMapping("/findMyDemand/{uid}")
    public ResponseEntity findMyDemand(@PathVariable Integer uid) {
        List<EtmsDemand> etmsDemandList = etmsDemandService.findMyDemand(uid);
        System.out.println(etmsDemandList);
        return new ResponseEntity(200, "查询成功", etmsDemandList);
    }

    //查询具体发布需求
    @RequestMapping ("/findDemandById/{did}")
    public ResponseEntity findDemandByid(@PathVariable Integer did){
        EtmsDemand etmsDemand = etmsDemandService.findDemandByid(did);
        return  new ResponseEntity(200,"查询成功",etmsDemand);
    }
    //搜索发布需求
    @RequestMapping ("/findDemandByName")
    public ResponseEntity findDemandByName(@RequestBody EtmsDemand etmsDemand){

        List<EtmsDemand> etmsDemandlist = etmsDemandService.findDemandByName(etmsDemand.getDemandTitle(), etmsDemand.getUserId());
        return  new ResponseEntity(200,"查询成功",etmsDemandlist);
    }
    //新增需求发布
    @RequestMapping("/addDemand")
    public ResponseEntity addDemand(@RequestBody EtmsDemand etmsDemand ){
        int i = etmsDemandService.addDemand(etmsDemand);
        if(i == 1){
            return  new ResponseEntity(200,"查询成功",i);
        } else {
            return  new ResponseEntity(401,"查询失败",i);
        }
    }


}
