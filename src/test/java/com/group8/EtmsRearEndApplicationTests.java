package com.group8;

import com.group8.dao.DemandDao;
import com.group8.dao.ResachAnwerDao;
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
    ResachAnwerDao resachAnwerDao;
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

    @Test
    void test1() {
        EtmsResachTopic etmsResachTopic = new EtmsResachTopic();
        ArrayList<EtmsResachTopic> tipices = new ArrayList<>();
        etmsResachTopic.setTopicName("2222");
        //System.out.println(etmsResachTopic);
        tipices.add(etmsResachTopic);
        for (int i = 0; i < 3; i++) {
            tipices.add(etmsResachTopic);

        }

        //etmsResearchTopic.addTopices(tipices);
        System.out.println(tipices);
    }

    @Test
    void tets() {

        List<EtmsResachTopic> etmsResachTopics = etmsResearchTopic.selectTopicById(1);
        for (EtmsResachTopic etmsResachTopic : etmsResachTopics) {
            System.out.println(etmsResachTopic);
        }

    }

    @Test
    void testfindAllTopice() {
        List<EtmsResachTopic> allTopic = etmsResearchTopic.findALLTopic();
        for (EtmsResachTopic etmsResachTopic : allTopic) {
            System.out.println(etmsResachTopic);
        }
    }

//    @Test
//    void  test9(){
//        EtmsResachAnwer etmsResachAnwer = new EtmsResachAnwer();
//        etmsResachAnwer.
//        resachAnwerDao.selectAnwerById()
//    }
//    @Test
//    void test3(){
//        EtmsResachTopic etmsResachTopic = new EtmsResachTopic();
//
//        for (int i = 0; i <3; i++) {
//            etmsResachTopic.setTopicName("1");
//
//        }
//        etmsResearchTopic.EtmsResachTopicaddOne(etmsResachTopic);
//    }
}
