package com.group8.controller;

import com.group8.entity.EtmsResachTopic;
import com.group8.entity.ResponseEntity;
import com.group8.service.ResachTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * description: EtmsResachTopicController <br>
 * date: 2021/12/16 4:58 上午 <br>
 * author: shesaifei <br>
 * version: 1.0 <br>
 */
@RestController
@RequestMapping("/topic")
public class ResachTopicController {
    @Autowired
    ResachTopicService etmsResachTopicService;

    //查询所有题目
    @RequestMapping("/findAll")
    public ResponseEntity<List<EtmsResachTopic>> findAll() {
        List<EtmsResachTopic> topicLsit = etmsResachTopicService.findALLTopic();
        return new ResponseEntity<>(200, "查询成功", topicLsit);
    }

    //新增一条题目
    @RequestMapping("/EtmsResachTopicaddOne")
    public ResponseEntity<EtmsResachTopic> EtmsResachTopicaddOne(@RequestBody EtmsResachTopic etmsResachTopic) {
        Integer i = etmsResachTopicService.EtmsResachTopicaddOne(etmsResachTopic);
        if (i > 0) {
            return new ResponseEntity(200, "新增成功", "成功新增" + i + "条数");
        } else {
            return new ResponseEntity(500, "新增失败", "服务器在维护中");
        }
    }
}
