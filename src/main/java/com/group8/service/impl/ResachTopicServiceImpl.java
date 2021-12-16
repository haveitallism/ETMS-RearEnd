package com.group8.service.impl;

import com.group8.dao.ResarchTopicDao;
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
    @Autowired
    ResarchTopicDao etmsResachTopicDao;

    @Override
    public List<EtmsResachTopic> findALL() {
        return etmsResachTopicDao.findALL();
    }

    @Override
    public Integer EtmsResachTopicaddOne(EtmsResachTopic etmsResachTopic) {
        return etmsResachTopicDao.EtmsResachTopicaddOne(etmsResachTopic);
    }
}
