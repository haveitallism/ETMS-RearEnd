package com.group8.dao;

import com.group8.entity.EtmsResachAnwer;
import com.group8.entity.EtmsResachTopic;

import java.util.List;

public interface ResearchTopicDao {
    List<EtmsResachTopic> findTopic();

    List<EtmsResachAnwer> findAnswer();

    int findNums();

    //新增一条题目
//    Integer etmsResachTopicaddOne(List<EtmsResachTopic> etmsResachTopic);
    Integer EtmsResachTopicaddOne(EtmsResachTopic etmsResachTopic);

    //查询所有题目
    List<EtmsResachTopic> findALLTopic();

    //查询题目对应的所有答案
    List<EtmsResachTopic> selectTopicById(Integer topicId);
//    int addTopices(@Param("topices")List<EtmsResachTopic> topices);
}
