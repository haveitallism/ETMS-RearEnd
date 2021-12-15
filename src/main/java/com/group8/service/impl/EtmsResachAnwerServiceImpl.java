package com.group8.service.impl;

import com.group8.dao.EtmsResachAnwerDao;
import com.group8.entity.EtmsResachAnwer;
import com.group8.service.EtmsResachAnwerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description: EtmsResachAnwerServiceImpl <br>
 * date: 2021/12/16 5:50 上午 <br>
 * author: shesaifei <br>
 * version: 1.0 <br>
 */
@Service
public class EtmsResachAnwerServiceImpl implements EtmsResachAnwerService {
    @Autowired(required = false)
    EtmsResachAnwerDao etmsResachAnwerDao;

    @Override
    public List<EtmsResachAnwer> findALL() {
        return etmsResachAnwerDao.findALL();
    }

    @Override
    public Integer addEtmsResachAnwerOne(EtmsResachAnwer etmsResachAnwer) {
        return etmsResachAnwerDao.addEtmsResachAnwerOne(etmsResachAnwer);
    }
}
