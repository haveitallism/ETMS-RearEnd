package com.group8.dao;

import com.group8.entity.EtmsResachTopic;

import java.util.List;

public interface EtmsResearchTopicDao {
    List<EtmsResachTopic> findAll(List topics);
}
