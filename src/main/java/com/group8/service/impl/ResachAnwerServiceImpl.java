package com.group8.service.impl;

import com.group8.dao.ResachAnwerDao;
import com.group8.dao.ResearchTopicDao;
import com.group8.dto.ceshiDto;
import com.group8.entity.EtmsResachAnwer;
import com.group8.entity.EtmsResachTopic;
import com.group8.service.ResachAnwerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * description: EtmsResachAnwerServiceImpl <br>
 * date: 2021/12/16 5:50 上午 <br>
 * author: shesaifei <br>
 * version: 1.0 <br>
 */
@Service
public class ResachAnwerServiceImpl implements ResachAnwerService {
    @Autowired(required = false)
    ResachAnwerDao etmsResachAnwerDao;

    @Autowired(required = false)
    ResearchTopicDao researchTopicDao;

    @Override
    public List<EtmsResachAnwer> findALL() {
        return etmsResachAnwerDao.findALL();
    }

    @Override
    public Integer addEtmsResachAnwerOne(List<ceshiDto> ansers, String topic) {
        EtmsResachTopic etmsResachTopic = new EtmsResachTopic();
        etmsResachTopic.setTopicName(topic);
//        System.out.println(etmsResachTopic);
        researchTopicDao.EtmsResachTopicaddOne(etmsResachTopic);
        ArrayList arrayList = new ArrayList();
        for (ceshiDto anser : ansers) {
            arrayList.add(anser.getAnsers());
        }
        Integer topicId = etmsResachTopic.getTopicId();
        return etmsResachAnwerDao.addEtmsResachAnwerOne(arrayList, topicId);
    }
}
