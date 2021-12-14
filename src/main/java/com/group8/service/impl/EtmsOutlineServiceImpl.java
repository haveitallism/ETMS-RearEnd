package com.group8.service.impl;

import com.group8.dao.EtmsOutlineDao;
import com.group8.service.EtmsOutlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class EtmsOutlineServiceImpl implements EtmsOutlineService {
    @Autowired(required = false)
    EtmsOutlineDao etmsOutlineDao;

    @Override
    public List<String> findAllCatalog(int itemid) {
        return etmsOutlineDao.findAllCatalog(itemid);
    }
}
