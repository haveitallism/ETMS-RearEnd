package com.group8;

import com.group8.dao.EtmsResearchAnswerDao;
import com.group8.dao.EtmsResearchTopicDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class EtmsRearEndApplicationTests {
@Autowired
EtmsResearchTopicDao etmsResearchTopic;
@Autowired
    EtmsResearchAnswerDao etmsResearchAnswerDao;
    @Test
    void contextLoads() {
        List all = etmsResearchAnswerDao.findAll();
        System.out.println(all);
        System.out.println(etmsResearchTopic.findAll(all));

    }

}
