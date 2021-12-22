package com.group8.controller;

import com.group8.dto.AnwersDto;
import com.group8.entity.EtmsResachAnwer;
import com.group8.entity.ResponseEntity;
import com.group8.service.ResachAnwerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * description: EtmsResachAnwerController <br>
 * date: 2021/12/16 5:31 上午 <br>
 * author: shesaifei <br>
 * version: 1.0 <br>
 */
@RestController
@RequestMapping("/anwer")
@CrossOrigin
public class ResachAnwerController {
    @Autowired
    ResachAnwerService etmsResachAnwerService;

    @RequestMapping("/findAll")
    public ResponseEntity<List<EtmsResachAnwer>> findAll() {
        List<EtmsResachAnwer> anwerList = etmsResachAnwerService.findALL();
        if (anwerList != null) {
            return new ResponseEntity<>(200, "查询成功", anwerList);
        } else {
            return new ResponseEntity<>(400, "查询失败", anwerList);
        }

    }

    @RequestMapping("addEtmsResachAnwerOne")
    public ResponseEntity<EtmsResachAnwer> addEtmsResachAnwerOne(@RequestBody AnwersDto anwersDto) {
        Integer i = etmsResachAnwerService.addEtmsResachAnwerOne(anwersDto.getAnswers(), anwersDto.getTopic());
        if (i > 0) {
            return new ResponseEntity(200, "新增成功", "成功新增" + i + "条数");
        } else {
            return new ResponseEntity(500, "新增失败", "服务器在维护中");
        }
//        System.out.println(anwersDto);
//       return new ResponseEntity(500, "新增失败", "服务器在维护中");
    }
}
