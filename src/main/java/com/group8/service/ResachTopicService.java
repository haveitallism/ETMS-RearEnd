package com.group8.service;

import com.group8.entity.EtmsResachTopic;

import java.util.List;

/**
 * description: EtmsResachTopicService <br>
 * date: 2021/12/15 8:02 下午 <br>
 * author: shesaifei <br>
 * version: 1.0 <br>
 */
public interface ResachTopicService {
    List<EtmsResachTopic> findALL();

    //新增一道题目
    Integer EtmsResachTopicaddOne(EtmsResachTopic etmsResachTopic);

    //查询指定题目下的所有答案
    List<EtmsResachTopic> selectTopicById(Integer topicId);

    //查询所有答案
    List<EtmsResachTopic> findALLTopic();
}
