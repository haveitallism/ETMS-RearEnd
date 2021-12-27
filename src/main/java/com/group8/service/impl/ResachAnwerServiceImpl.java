package com.group8.service.impl;

import com.group8.dao.DemandDao;
import com.group8.dao.ResachAnwerDao;
import com.group8.dao.ResearchTopicDao;
import com.group8.dto.AnwersDto;
import com.group8.entity.EtmsResachAnwer;
import com.group8.entity.EtmsResachTopic;
import com.group8.service.ResachAnwerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * description: EtmsResachAnwerServiceImpl <br>
 * date: 2021/12/16 5:50 上午 <br>
 * author: shesaifei <br>
 * version: 1.0 <br>
 */
@Service
@Transactional
public class ResachAnwerServiceImpl implements ResachAnwerService {
    @Autowired(required = false)
    ResachAnwerDao etmsResachAnwerDao;

    @Autowired(required = false)
    ResearchTopicDao researchTopicDao;
    @Autowired(required = false)
    DemandDao demandDao;

    @Override
    public List<EtmsResachAnwer> findALL() {
        return etmsResachAnwerDao.findALL();
    }

    @Override
    public EtmsResachAnwer selectAnwerById(Integer answerId) {
        return etmsResachAnwerDao.selectAnwerById(answerId);
    }

    @Override
    public Integer addEtmsResachAnwerOne(AnwersDto anwersDto) {
        for (EtmsResachTopic topic : anwersDto.getTopics()) {
            System.out.println(topic);
            researchTopicDao.EtmsResachTopicaddOne(topic);
            Integer topicId = topic.getTopicId();
            System.out.println("DemandTableId=" + topic.getDemandTableId());
            etmsResachAnwerDao.addEtmsResachAnwerOne(topic.getEtmsResachAnwers(), topicId);
        }

        return 1;
    }
}
