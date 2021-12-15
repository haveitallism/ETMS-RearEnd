package com.group8.service;

import com.group8.entity.EtmsResachTopic;

import java.util.List;

/**
 * description: EtmsResachTopicService <br>
 * date: 2021/12/15 8:02 下午 <br>
 * author: shesaifei <br>
 * version: 1.0 <br>
 */
public interface EtmsResachTopicService {
    List<EtmsResachTopic> findALL();

    Integer EtmsResachTopicaddOne(EtmsResachTopic etmsResachTopic);
}
