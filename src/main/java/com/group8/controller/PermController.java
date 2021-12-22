package com.group8.controller;

import com.group8.entity.EtmsPerm;
import com.group8.entity.ResponseEntity;
import com.group8.service.PermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/perm")
public class PermController {
    @Autowired(required = false)
    PermService permService;

    @RequestMapping("/findAll")
    public ResponseEntity<List<EtmsPerm>> findAll(){
        List<EtmsPerm> permList = permService.findAll();
        if (permList.size() > 0) {
            return new ResponseEntity<>(200, "查询成功", permList);
        }else {
            return new ResponseEntity<>(500, "查询失败", null);
        }
    }

}
