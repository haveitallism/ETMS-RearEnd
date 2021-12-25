package com.group8;

import com.group8.dao.DemandDao;
import com.group8.dao.DeptDao;
import com.group8.dao.ResachAnwerDao;
import com.group8.dao.ResearchTopicDao;
import com.group8.entity.EtmsDemand;
import com.group8.entity.EtmsDept;
import com.group8.entity.EtmsResachTopic;
import com.group8.service.DemandService;
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
    @Autowired
    DemandService demandService;
    @Autowired
    DeptDao deptDao;

    @Test
    void contextLoads() {
        List<EtmsResachTopic> topic = etmsResearchTopic.findTopic();
        System.out.println(topic);
        for (EtmsResachTopic etmsResachTopic : topic) {
            System.out.println(etmsResachTopic);
        }
    }

    @Test
    void test22() {
//        EtmsDept etmsDept = new EtmsDept();
//        etmsDept.setDeptId(1);
        //System.out.println(deptDao.selectEtmsDeptById(1));
        EtmsDept etmsDept = new EtmsDept();
        etmsDept.setDeptId(1);
        etmsDept.getEtmsDemands();
        System.out.println(deptDao.findAlls(etmsDept));
    }

    @Test
    void contextLoads1() {
        EtmsDemand byid = demandDao.findByid(1);
        System.out.println(byid);
    }

    @Test
    void contextLoads2() {
        EtmsDemand etmsDemand = new EtmsDemand();
        etmsDemand.setDemandDept(1);
        etmsDemand.setDemandTitle("ui啊撒骨灰对哇给我");
        List<EtmsDemand> all = demandService.findAllDemand(etmsDemand);
        System.out.println(all);
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
