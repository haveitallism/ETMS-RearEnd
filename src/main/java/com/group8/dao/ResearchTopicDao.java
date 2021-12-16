package com.group8.dao;

import com.group8.entity.EtmsResachAnwer;
import com.group8.entity.EtmsResachTopic;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResearchTopicDao {
    List<EtmsResachTopic> findTopic();
    List<EtmsResachAnwer> findAnswer();
    int findNums();
    //新增一条题目
    Integer EtmsResachTopicaddOne(EtmsResachTopic etmsResachTopic);
}
