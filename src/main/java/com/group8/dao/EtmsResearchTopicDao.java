package com.group8.dao;

import com.group8.entity.EtmsResachAnwer;
import com.group8.entity.EtmsResachTopic;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EtmsResearchTopicDao {
    List<EtmsResachTopic> findTopic(@Param("sss") List topics);
    List<EtmsResachAnwer> findAnswer(List answers);
}
