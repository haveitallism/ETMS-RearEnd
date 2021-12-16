package com.group8;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EtmsRearEndApplicationTests {
    @Autowired
    ResarchTopicDao etmsResachTopicDao;

    @Autowired
    ResachAnwerDao etmsResachAnwerDao;
    @Autowired
    ResachAnwerService etmsResachAnwerService;

    @Test
    void test3() {
        for (EtmsResachAnwer etmsResachAnwer : etmsResachAnwerService.findALL()) {
            System.out.println(etmsResachAnwer);
        }
    }


    @Test
    void contextLoads() {
        for (EtmsResachTopic resachTopic : etmsResachTopicDao.findALL()) {
            System.out.println(resachTopic);
        }
    }

    @Test
    void etmsResachAnweraddone() {
        EtmsResachAnwer etmsResachAnwer = new EtmsResachAnwer();
//        etmsResachAnwer.setAnswer_id(10);
        etmsResachAnwer.setAnswer_content("18个月");
        Integer integer = etmsResachAnwerDao.addEtmsResachAnwerOne(etmsResachAnwer);
        System.out.println(etmsResachAnwer.getAnswer_id());
        System.out.println(integer);
    }

    @Test
    void test1() {
        for (EtmsResachAnwer etmsResachAnwer : etmsResachAnwerDao.findALL()) {
            System.out.println(etmsResachAnwer);
        }

    }

}
