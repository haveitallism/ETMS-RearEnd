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
    //查找所有的需求
    @Override
    public List<EtmsDemand> findAllDemand() {
        List<EtmsDemand> etmsDemandList = etmsDemandDao.findAll();
        return etmsDemandList;
    }
    //查找个人发布的需求
    @Override
    public List<EtmsDemand> findMyDemand(int uid) {
        List<EtmsDemand> etmsDemandList = etmsDemandDao.findByuid(uid);
        return etmsDemandList;
    }
    //新增需求
    @Override
    public int addDemand(EtmsDemand etmsDemand) {
        return etmsDemandDao.addOne(etmsDemand);
    }
    //查询具体需求
    @Override
    public EtmsDemand findDemandByid(Integer did) {
        return etmsDemandDao.findByid(did);
    }
    //搜索需求
    @Override
    public List<EtmsDemand> findDemandByName(String title, Integer uid) {
        return etmsDemandDao.findByName(title,uid);
    }
}
