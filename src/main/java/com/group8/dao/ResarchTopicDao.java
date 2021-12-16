package com.group8.dao;

import com.group8.entity.EtmsResachTopic;

import java.util.List;

/**
 * description: EtmsResachTopicDao <br>
 * date: 2021/12/15 4:08 下午 <br>
 * author: shesaifei <br>
 * version: 1.0 <br>
 */

public interface ResarchTopicDao {
    //新增一条题目
    Integer EtmsResachTopicaddOne(EtmsResachTopic etmsResachTopic);

    //查询所有题目
    List<EtmsResachTopic> findALL();
}
