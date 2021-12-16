package com.group8;

import com.group8.dao.ResearchTopicDao;
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
    @Test
    void contextLoads() {
        List s = new ArrayList();
        s.add(1);
        s.add(2);
        s.add(3);
        List<EtmsResachTopic> topic = etmsResearchTopic.findTopic(s);
        System.out.println(topic);

    }

}
