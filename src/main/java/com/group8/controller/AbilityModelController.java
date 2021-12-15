package com.group8.controller;

import com.group8.dto.AbilityModelSubject;
import com.group8.entity.EtmsAbilityModel;
import com.group8.entity.ResponseEntity;
import com.group8.service.AbilityModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/abilityModel")
public class AbilityModelController {
    @Autowired(required = false)
    AbilityModelService abilityModelService;

    /**
     * 根据主体及主体id查询对应的能力模型
     * @param abilityModelSubject 包含主体及主体id的对象
     * @return 返回能力模型对象集合
     */
    @RequestMapping("/findAll")
    public ResponseEntity<List<EtmsAbilityModel>> findAll(@RequestBody AbilityModelSubject abilityModelSubject){
        List<EtmsAbilityModel> abilityModelList = abilityModelService.findAll(abilityModelSubject);
        return new ResponseEntity<>(200, abilityModelList);
    }
}

