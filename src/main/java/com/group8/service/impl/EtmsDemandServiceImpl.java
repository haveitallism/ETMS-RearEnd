package com.group8.service.impl;

import com.group8.dao.EtmsDemandDao;
import com.group8.entity.EtmsDemand;
import com.group8.service.EtmsDemandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EtmsDemandServiceImpl implements EtmsDemandService {
    @Autowired
    EtmsDemandDao etmsDemandDao;
    @Override
    public List<EtmsDemand> findAllDemand() {
        List<EtmsDemand> etmsDemandList = etmsDemandDao.findAll();
        return etmsDemandList;
    }

    @Override
    public List<EtmsDemand> findMyDemand(int uid) {
        List<EtmsDemand> etmsDemandList = etmsDemandDao.findByuid(uid);
        return etmsDemandList;
    }

    @Override
    public int addDemand(EtmsDemand etmsDemand) {
        return etmsDemandDao.addOne(etmsDemand);
    }
}
