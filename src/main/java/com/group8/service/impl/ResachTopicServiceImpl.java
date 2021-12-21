package com.group8.service.impl;

import com.group8.dao.ResearchTopicDao;
import com.group8.entity.EtmsResachTopic;
import com.group8.service.ResachTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description: EtmsResachTopicServiceImpl <br>
 * date: 2021/12/16 4:51 上午 <br>
 * author: shesaifei <br>
 * version: 1.0 <br>
 */
@Service
public class ResachTopicServiceImpl implements ResachTopicService {
    @Autowired(required = false)
    ResearchTopicDao researchTopicDao;

    @Override
    public List<EtmsResachTopic> findALL() {
        return researchTopicDao.findTopic();
    }

    @Override
    public Integer EtmsResachTopicaddOne(EtmsResachTopic etmsResachTopic) {
        return researchTopicDao.EtmsResachTopicaddOne(etmsResachTopic);
    }

    @Override
    public List<EtmsResachTopic> findALLTopic() {
        return researchTopicDao.findALLTopic();
    }
}
