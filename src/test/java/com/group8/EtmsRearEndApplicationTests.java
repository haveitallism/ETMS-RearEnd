package com.group8;

import com.group8.dao.DemandDao;
import com.group8.dao.ResearchTopicDao;
import com.group8.entity.EtmsDemand;
import com.group8.entity.EtmsResachTopic;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class EtmsRearEndApplicationTests {
@Autowired
ResearchTopicDao etmsResearchTopic;
@Autowired
    DemandDao demandDao;
    @Test
    void contextLoads() {
        List<EtmsResachTopic> topic = etmsResearchTopic.findTopic();
        System.out.println(topic);
        for (EtmsResachTopic etmsResachTopic : topic) {
            System.out.println(etmsResachTopic);
        }
    }
    @Test
    void contextLoads1() {
        EtmsDemand byid = demandDao.findByid(1);
        System.out.println(byid);
    }
}
